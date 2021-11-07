package cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CacheDAO {
    private ArrayList<CacheDTO> caches = new ArrayList<CacheDTO>();

    public ArrayList<CacheDTO> searchCache(int period) throws IOException {
        try {
            String username = System.getProperty("user.name");
            FileInputStream data_0 = new FileInputStream("C:\\Users\\"+username+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\data_0");

            byte[] header = new byte[0x2000]; // 0x0 ~ 0x2000 : block file header
            byte[] index_block = new byte[0x24]; // 0x24 : index record size

            int tmp, entry_count = 0;
            tmp = data_0.read(header);
            entry_count = getEntryCount(header);

            int[] entry_addr = new int[entry_count];
            for(int i = 0; i < entry_count; i++){
                tmp = data_0.read(index_block);
                entry_addr[i] = getEntryOffset(index_block);
            }
            // if address is -1, it means there is no entry.

            entry_addr = sortEntryAddress(entry_addr, entry_count);

            FileInputStream data_1 = new FileInputStream("C:\\Users\\"+username+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache\\data_1");

            int i = 0, current_offset = 0;
            while(entry_addr[i] == -1) i++; // skip until not -1

            // parse each entry's data
            for(; i < entry_count; i++){
                CacheDTO cache = new CacheDTO();

                int padding_size = entry_addr[i] - current_offset;
                byte padding[] = new byte[padding_size];
                tmp = data_1.read(padding);

                byte entry_header[] = new byte[0x60];
                tmp = data_1.read(entry_header);

                // get url
                byte test[] = new byte[1]; // if key data is not found.
                tmp = data_1.read(test);
                if(test[0] == 0){
                    current_offset = entry_addr[i] + 0x60 + 1;
                    continue;
                }

                // - parse detail url from key_data (detail url is located in third part)
                int key_len = getKeyLength(entry_header);
                byte key_data[] = new byte[key_len-1];
                tmp = data_1.read(key_data);
                String key_str = new String(key_data, "UTF-8");
                int j = 0;
                for(int flag = 0; flag < 2 && j < key_str.length(); j++){
                    if(key_str.charAt(j) == ' ') flag += 1;
                }
                char url[] = new char[key_str.length() - j];
                key_str.getChars(j, key_str.length(), url, 0);
                cache.setUrl(String.valueOf(url));

                // get create time
                cache.setCreate_time(getTimeString(entry_header));

                // get file size
                // --> should decide how we use this data.

                caches.add(cache);

                current_offset = entry_addr[i] + 0x60 + key_len;
            }

            data_0.close();
            data_1.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return caches;
    }

    // get entry count in data_0 file
    private static int getEntryCount(byte[] header){
        int count = 0;
        for(int i = 0x13; i >= 0x10; i--){
            count = count << 8;
            count += Byte.toUnsignedInt(header[i]);
        }
        return count;
    }

    // get entry key length in data_1 file
    private static int getKeyLength(byte[] header){
        int length = 0;
        for(int i = 0x23; i >= 0x20; i--){
            length = length << 8;
            length += Byte.toUnsignedInt(header[i]);
        }
        return length;
    }

    // convert Chrome Timestamp to readable time string
    private static String getTimeString(byte[] header){
        long webKitTimestamp = 0;
        for(int i = 31; i >= 24; i--){
            webKitTimestamp = webKitTimestamp << 8;
            webKitTimestamp += Byte.toUnsignedInt(header[i]);
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
    private static int getEntryOffset(byte[] index){
        // 0x19(25), 0x18(24) index --> offset
        // 0x20(16) index --> file number
        // offset * block size + 0x2000(8192) --> offset

        int address = 8192;
        int fileNum = index[26];
        int offset = Byte.toUnsignedInt(index[25]);
        offset = offset << 8;
        offset += Byte.toUnsignedInt(index[24]);

        if(fileNum == 1) address += offset * 256; // 0x100
        else if(fileNum == 2) address += offset * 1024; // 0x400
        else if(fileNum == 3) address += offset * 4096; // 0x1000
        else address = -1; // none..

        return address;
    }

    // sort entry offset in index_0 hash table.
    private static int[] sortEntryAddress(int[] addr, int size){
        int save = 0;
        for(int i = 1; i < size; i++){
            int j = i;
            save = addr[i];
            while(j > 0 && addr[j-1] > save){
                addr[j] = addr[j-1];
                j--;
            }
            addr[j] = save;
        }
        return addr;
    }
}
