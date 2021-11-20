package cache;

import util.CopyFile;
import util.Time;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CacheDAO {
    private static CacheDAO instance = new CacheDAO();

    private CacheDAO(){}

    public static CacheDAO getInstance(){
        return instance;
    }

    public ArrayList<CacheDTO> records = new ArrayList<CacheDTO>();

    public ArrayList<CacheDTO> searchRecord(int days) throws IOException {
        try {
            String username = System.getProperty("user.name");

            CopyFile copy = CopyFile.getInstance();
            copy.makeCache("data_0");
            copy.makeCache("data_1");
            copy.makeCache("data_3");

            RandomAccessFile data_0 = new RandomAccessFile("C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\new_data_0", "r");
            RandomAccessFile data_1 = new RandomAccessFile("C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\new_data_1", "r");
            RandomAccessFile data_3 = new RandomAccessFile("C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\new_data_3", "r");

            Time time = Time.getInstance();
            String time_str = time.subDays(days);

            byte[] block_header = new byte[0x2000];
            byte[] index_block = new byte[0x24];

            int entry_count = 0;
            data_0.read(block_header);
            entry_count = convertHexToDec(block_header, 0x13, 0x10);

            int[] entry_addr = new int[entry_count];
            for (int i = 0; i < entry_count; i++) {
                data_0.read(index_block);
                byte entry_hex_addr[] = Arrays.copyOfRange(index_block, 24, 28); // 24 ~ 27

                entry_addr[i] = convertAddrToOffset(entry_hex_addr);
            }

            // parse each entry's data
            for (int i = 0; i < entry_count; i++) {
                if (entry_addr[i] < 0) continue; // skip if entry address is -1.

                CacheDTO cache = new CacheDTO();
                byte entry_header[] = new byte[0x60];

                data_1.seek(entry_addr[i]);
                data_1.read(entry_header);

                // get create time
                String time_test = getTimeString(entry_header, time_str);
                if (time_test.equals("-1")) continue;
                cache.setCreate_time(time_test);

                // ready to parse url from key_data
                byte test[] = new byte[1]; // check whether the key data exists.
                data_1.read(test);
                if (test[0] == 0) {
                    // case of no key_data -> check metadata then can get url
                    continue;
                }

                // - parse detail url from key_data (detail url is located in third part)
                int key_len = convertHexToDec(entry_header, 0x23, 0x20);
                byte key_data[] = new byte[key_len - 1];
                data_1.read(key_data);
                String key_str = new String(key_data, "UTF-8");
                int j = 0;
                for (int flag = 0; flag < 2 && j < key_str.length(); j++) {
                    if (key_str.charAt(j) == ' ') flag += 1;
                }
                char url[] = new char[key_str.length() - j];
                key_str.getChars(j, key_str.length(), url, 0);
                cache.setUrl(String.valueOf(url));

                // cache data size
                int file_size = convertHexToDec(entry_header, 0x2F, 0x2C);
                cache.setData_size(String.valueOf(file_size));

                // get meta data
                int metadata_size = convertHexToDec(entry_header, 0x2B, 0x28);
                byte[] metadata = new byte[metadata_size];

                byte[] metadata_addr = Arrays.copyOfRange(entry_header, 0x38, 0x3C); // 0x38 ~ 0x3B
                int metadata_offset = convertAddrToOffset(metadata_addr);

                data_3.seek(metadata_offset);
                data_3.read(metadata);

                // get meta data - content_type
                int content_type_offset = indexOf(metadata, "content-type:".getBytes());
                if(content_type_offset != -1){
                    byte[] content_type = new byte[50];
                    data_3.seek(metadata_offset + content_type_offset);
                    data_3.read(content_type);

                    int end_offset = indexOf(content_type, ";".getBytes());
                    if(end_offset < 0x0D){
                        end_offset = indexOf(content_type, new byte[1]);
                    }
                    byte[] content_type_byte = Arrays.copyOfRange(content_type, 0x0D, end_offset);
                    String content_type_str = new String(content_type_byte, Charset.defaultCharset());

                    cache.setData_type(content_type_str);
                } else {
                    cache.setData_type("unknown");
                }

                // get meta data - file_name
                int file_name_offset = indexOf(metadata, "filename=".getBytes());
                if(file_name_offset != -1){
                    byte[] file_name = new byte[30];
                    data_3.seek(metadata_offset + file_name_offset);
                    data_3.read(file_name);

                    int end_offset = indexOf(file_name, new byte[1]);
                    byte[] file_name_byte = Arrays.copyOfRange(file_name, 0x09, end_offset);
                    String file_name_str = new String(file_name_byte, Charset.defaultCharset());

                    cache.setData_name(file_name_str);
                } else {
                    // parse file name from url
                    String url_str = String.valueOf(url);
                    int start_point = url_str.lastIndexOf("/") + 1;
                    int end_point = 0;
//                    url_str = url_str.substring(start_point);
//                    j = url_str.indexOf(".");
                    for (j = start_point; j < url_str.length(); j++) {
                        if (url_str.charAt(j) == '?') break; // The end point is where the '?' is.
                    }
                    end_point = j;
                    String filename_url = url_str.substring(start_point, end_point);

                    if (!filename_url.contains(".") || filename_url.contains("search") || filename_url.contains("=")) {
                        cache.setData_name("unknown");
                    } else {
                        cache.setData_name(filename_url);
                    }

                    cache.setId(Integer.toString(i + 1));
                    records.add(cache);
                }
            }

            data_0.close();
            data_1.close();
            data_3.close();
        } catch(IOException | SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    // convert hexdecimal address(length) to decimal address(length) (little endian ver.)
    private static int convertHexToDec(byte[] header, int start, int end) {
        int num = 0;
        for (int i = start; i >= end; i--) {
            num = num << 8;
            num += Byte.toUnsignedInt(header[i]);
        }
        return num;
    }

    // convert Chrome Timestamp to readable time string
    private static String getTimeString(byte[] header, String days) throws SQLException {
        long webKitTimestamp = 0;
        for(int i = 31; i >= 24; i--){
            webKitTimestamp = webKitTimestamp << 8;
            webKitTimestamp += Byte.toUnsignedInt(header[i]);
        }

        if(String.valueOf(webKitTimestamp).compareTo(days) < 0){
            return "-1";
        }

        long epochStart = LocalDateTime
                .from(LocalDateTime.of(1601, Month.JANUARY, 1, 0, 0))
                .until(LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0), ChronoUnit.MICROS);
        long delta = webKitTimestamp - epochStart;
        LocalDateTime localdatetime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(TimeUnit.MICROSECONDS.toMillis(delta)), ZoneId.systemDefault());
        return localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // convert cache address to offset in file(integer)
    private static int convertAddrToOffset(byte[] hexAddr){
        int address = 8192;
        int fileNum = hexAddr[2];
        int offset = Byte.toUnsignedInt(hexAddr[1]);
        offset = offset << 8;
        offset += Byte.toUnsignedInt(hexAddr[0]);

        if(fileNum == 1) address += offset * 256; // 0x100
        else if(fileNum == 2) address += offset * 1024; // 0x400
        else if(fileNum == 3) address += offset * 4096; // 0x1000
        else address = -1; // none..

        return address;
    }

    // get index of byte array in outer byte array
    private int indexOf(byte[] outerArray, byte[] smallerArray) {
        for(int i = 0; i < outerArray.length - smallerArray.length+1; ++i) {
            boolean found = true;
            for(int j = 0; j < smallerArray.length; ++j) {
                if (outerArray[i+j] != smallerArray[j]) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }
        return -1;
    }

    public String getUrl(int idx){
        return records.get(idx).getUrl();
    }

    public String getCreate_time(int idx){
        return records.get(idx).getCreate_time();
    }

    public int getRecordCnt(){
        return records.size();
    }

}
