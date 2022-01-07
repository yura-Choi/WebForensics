package cipher;

public class AES
{
    private static final int NB = 4;
    private static final int NR = 14;
    private static final int NK = 8;

    private static final byte SBOX[] =
            {
                    (byte)0x63, (byte)0x7c, (byte)0x77, (byte)0x7b, (byte)0xf2, (byte)0x6b, (byte)0x6f, (byte)0xc5, (byte)0x30, (byte)0x01, (byte)0x67, (byte)0x2b, (byte)0xfe, (byte)0xd7, (byte)0xab, (byte)0x76,
                    (byte)0xca, (byte)0x82, (byte)0xc9, (byte)0x7d, (byte)0xfa, (byte)0x59, (byte)0x47, (byte)0xf0, (byte)0xad, (byte)0xd4, (byte)0xa2, (byte)0xaf, (byte)0x9c, (byte)0xa4, (byte)0x72, (byte)0xc0,
                    (byte)0xb7, (byte)0xfd, (byte)0x93, (byte)0x26, (byte)0x36, (byte)0x3f, (byte)0xf7, (byte)0xcc, (byte)0x34, (byte)0xa5, (byte)0xe5, (byte)0xf1, (byte)0x71, (byte)0xd8, (byte)0x31, (byte)0x15,
                    (byte)0x04, (byte)0xc7, (byte)0x23, (byte)0xc3, (byte)0x18, (byte)0x96, (byte)0x05, (byte)0x9a, (byte)0x07, (byte)0x12, (byte)0x80, (byte)0xe2, (byte)0xeb, (byte)0x27, (byte)0xb2, (byte)0x75,
                    (byte)0x09, (byte)0x83, (byte)0x2c, (byte)0x1a, (byte)0x1b, (byte)0x6e, (byte)0x5a, (byte)0xa0, (byte)0x52, (byte)0x3b, (byte)0xd6, (byte)0xb3, (byte)0x29, (byte)0xe3, (byte)0x2f, (byte)0x84,
                    (byte)0x53, (byte)0xd1, (byte)0x00, (byte)0xed, (byte)0x20, (byte)0xfc, (byte)0xb1, (byte)0x5b, (byte)0x6a, (byte)0xcb, (byte)0xbe, (byte)0x39, (byte)0x4a, (byte)0x4c, (byte)0x58, (byte)0xcf,
                    (byte)0xd0, (byte)0xef, (byte)0xaa, (byte)0xfb, (byte)0x43, (byte)0x4d, (byte)0x33, (byte)0x85, (byte)0x45, (byte)0xf9, (byte)0x02, (byte)0x7f, (byte)0x50, (byte)0x3c, (byte)0x9f, (byte)0xa8,
                    (byte)0x51, (byte)0xa3, (byte)0x40, (byte)0x8f, (byte)0x92, (byte)0x9d, (byte)0x38, (byte)0xf5, (byte)0xbc, (byte)0xb6, (byte)0xda, (byte)0x21, (byte)0x10, (byte)0xff, (byte)0xf3, (byte)0xd2,
                    (byte)0xcd, (byte)0x0c, (byte)0x13, (byte)0xec, (byte)0x5f, (byte)0x97, (byte)0x44, (byte)0x17, (byte)0xc4, (byte)0xa7, (byte)0x7e, (byte)0x3d, (byte)0x64, (byte)0x5d, (byte)0x19, (byte)0x73,
                    (byte)0x60, (byte)0x81, (byte)0x4f, (byte)0xdc, (byte)0x22, (byte)0x2a, (byte)0x90, (byte)0x88, (byte)0x46, (byte)0xee, (byte)0xb8, (byte)0x14, (byte)0xde, (byte)0x5e, (byte)0x0b, (byte)0xdb,
                    (byte)0xe0, (byte)0x32, (byte)0x3a, (byte)0x0a, (byte)0x49, (byte)0x06, (byte)0x24, (byte)0x5c, (byte)0xc2, (byte)0xd3, (byte)0xac, (byte)0x62, (byte)0x91, (byte)0x95, (byte)0xe4, (byte)0x79,
                    (byte)0xe7, (byte)0xc8, (byte)0x37, (byte)0x6d, (byte)0x8d, (byte)0xd5, (byte)0x4e, (byte)0xa9, (byte)0x6c, (byte)0x56, (byte)0xf4, (byte)0xea, (byte)0x65, (byte)0x7a, (byte)0xae, (byte)0x08,
                    (byte)0xba, (byte)0x78, (byte)0x25, (byte)0x2e, (byte)0x1c, (byte)0xa6, (byte)0xb4, (byte)0xc6, (byte)0xe8, (byte)0xdd, (byte)0x74, (byte)0x1f, (byte)0x4b, (byte)0xbd, (byte)0x8b, (byte)0x8a,
                    (byte)0x70, (byte)0x3e, (byte)0xb5, (byte)0x66, (byte)0x48, (byte)0x03, (byte)0xf6, (byte)0x0e, (byte)0x61, (byte)0x35, (byte)0x57, (byte)0xb9, (byte)0x86, (byte)0xc1, (byte)0x1d, (byte)0x9e,
                    (byte)0xe1, (byte)0xf8, (byte)0x98, (byte)0x11, (byte)0x69, (byte)0xd9, (byte)0x8e, (byte)0x94, (byte)0x9b, (byte)0x1e, (byte)0x87, (byte)0xe9, (byte)0xce, (byte)0x55, (byte)0x28, (byte)0xdf,
                    (byte)0x8c, (byte)0xa1, (byte)0x89, (byte)0x0d, (byte)0xbf, (byte)0xe6, (byte)0x42, (byte)0x68, (byte)0x41, (byte)0x99, (byte)0x2d, (byte)0x0f, (byte)0xb0, (byte)0x54, (byte)0xbb, (byte)0x16
            };

    private static final byte RSBOX[] =
            {
                    (byte)0x52, (byte)0x09, (byte)0x6a, (byte)0xd5, (byte)0x30, (byte)0x36, (byte)0xa5, (byte)0x38, (byte)0xbf, (byte)0x40, (byte)0xa3, (byte)0x9e, (byte)0x81, (byte)0xf3, (byte)0xd7, (byte)0xfb,
                    (byte)0x7c, (byte)0xe3, (byte)0x39, (byte)0x82, (byte)0x9b, (byte)0x2f, (byte)0xff, (byte)0x87, (byte)0x34, (byte)0x8e, (byte)0x43, (byte)0x44, (byte)0xc4, (byte)0xde, (byte)0xe9, (byte)0xcb,
                    (byte)0x54, (byte)0x7b, (byte)0x94, (byte)0x32, (byte)0xa6, (byte)0xc2, (byte)0x23, (byte)0x3d, (byte)0xee, (byte)0x4c, (byte)0x95, (byte)0x0b, (byte)0x42, (byte)0xfa, (byte)0xc3, (byte)0x4e,
                    (byte)0x08, (byte)0x2e, (byte)0xa1, (byte)0x66, (byte)0x28, (byte)0xd9, (byte)0x24, (byte)0xb2, (byte)0x76, (byte)0x5b, (byte)0xa2, (byte)0x49, (byte)0x6d, (byte)0x8b, (byte)0xd1, (byte)0x25,
                    (byte)0x72, (byte)0xf8, (byte)0xf6, (byte)0x64, (byte)0x86, (byte)0x68, (byte)0x98, (byte)0x16, (byte)0xd4, (byte)0xa4, (byte)0x5c, (byte)0xcc, (byte)0x5d, (byte)0x65, (byte)0xb6, (byte)0x92,
                    (byte)0x6c, (byte)0x70, (byte)0x48, (byte)0x50, (byte)0xfd, (byte)0xed, (byte)0xb9, (byte)0xda, (byte)0x5e, (byte)0x15, (byte)0x46, (byte)0x57, (byte)0xa7, (byte)0x8d, (byte)0x9d, (byte)0x84,
                    (byte)0x90, (byte)0xd8, (byte)0xab, (byte)0x00, (byte)0x8c, (byte)0xbc, (byte)0xd3, (byte)0x0a, (byte)0xf7, (byte)0xe4, (byte)0x58, (byte)0x05, (byte)0xb8, (byte)0xb3, (byte)0x45, (byte)0x06,
                    (byte)0xd0, (byte)0x2c, (byte)0x1e, (byte)0x8f, (byte)0xca, (byte)0x3f, (byte)0x0f, (byte)0x02, (byte)0xc1, (byte)0xaf, (byte)0xbd, (byte)0x03, (byte)0x01, (byte)0x13, (byte)0x8a, (byte)0x6b,
                    (byte)0x3a, (byte)0x91, (byte)0x11, (byte)0x41, (byte)0x4f, (byte)0x67, (byte)0xdc, (byte)0xea, (byte)0x97, (byte)0xf2, (byte)0xcf, (byte)0xce, (byte)0xf0, (byte)0xb4, (byte)0xe6, (byte)0x73,
                    (byte)0x96, (byte)0xac, (byte)0x74, (byte)0x22, (byte)0xe7, (byte)0xad, (byte)0x35, (byte)0x85, (byte)0xe2, (byte)0xf9, (byte)0x37, (byte)0xe8, (byte)0x1c, (byte)0x75, (byte)0xdf, (byte)0x6e,
                    (byte)0x47, (byte)0xf1, (byte)0x1a, (byte)0x71, (byte)0x1d, (byte)0x29, (byte)0xc5, (byte)0x89, (byte)0x6f, (byte)0xb7, (byte)0x62, (byte)0x0e, (byte)0xaa, (byte)0x18, (byte)0xbe, (byte)0x1b,
                    (byte)0xfc, (byte)0x56, (byte)0x3e, (byte)0x4b, (byte)0xc6, (byte)0xd2, (byte)0x79, (byte)0x20, (byte)0x9a, (byte)0xdb, (byte)0xc0, (byte)0xfe, (byte)0x78, (byte)0xcd, (byte)0x5a, (byte)0xf4,
                    (byte)0x1f, (byte)0xdd, (byte)0xa8, (byte)0x33, (byte)0x88, (byte)0x07, (byte)0xc7, (byte)0x31, (byte)0xb1, (byte)0x12, (byte)0x10, (byte)0x59, (byte)0x27, (byte)0x80, (byte)0xec, (byte)0x5f,
                    (byte)0x60, (byte)0x51, (byte)0x7f, (byte)0xa9, (byte)0x19, (byte)0xb5, (byte)0x4a, (byte)0x0d, (byte)0x2d, (byte)0xe5, (byte)0x7a, (byte)0x9f, (byte)0x93, (byte)0xc9, (byte)0x9c, (byte)0xef,
                    (byte)0xa0, (byte)0xe0, (byte)0x3b, (byte)0x4d, (byte)0xae, (byte)0x2a, (byte)0xf5, (byte)0xb0, (byte)0xc8, (byte)0xeb, (byte)0xbb, (byte)0x3c, (byte)0x83, (byte)0x53, (byte)0x99, (byte)0x61,
                    (byte)0x17, (byte)0x2b, (byte)0x04, (byte)0x7e, (byte)0xba, (byte)0x77, (byte)0xd6, (byte)0x26, (byte)0xe1, (byte)0x69, (byte)0x14, (byte)0x63, (byte)0x55, (byte)0x21, (byte)0x0c, (byte)0x7d
            };

    private static final int Rcon[] =
            {
                    0x8d000000, 0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000, 0x1b000000, 0x36000000, 0x6c000000, 0xd8000000, 0xab000000, 0x4d000000, 0x9a000000,
                    0x2f000000, 0x5e000000, 0xbc000000, 0x63000000, 0xc6000000, 0x97000000, 0x35000000, 0x6a000000, 0xd4000000, 0xb3000000, 0x7d000000, 0xfa000000, 0xef000000, 0xc5000000, 0x91000000, 0x39000000,
                    0x72000000, 0xe4000000, 0xd3000000, 0xbd000000, 0x61000000, 0xc2000000, 0x9f000000, 0x25000000, 0x4a000000, 0x94000000, 0x33000000, 0x66000000, 0xcc000000, 0x83000000, 0x1d000000, 0x3a000000,
                    0x74000000, 0xe8000000, 0xcb000000, 0x8d000000, 0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000, 0x1b000000, 0x36000000, 0x6c000000, 0xd8000000,
                    0xab000000, 0x4d000000, 0x9a000000, 0x2f000000, 0x5e000000, 0xbc000000, 0x63000000, 0xc6000000, 0x97000000, 0x35000000, 0x6a000000, 0xd4000000, 0xb3000000, 0x7d000000, 0xfa000000, 0xef000000,
                    0xc5000000, 0x91000000, 0x39000000, 0x72000000, 0xe4000000, 0xd3000000, 0xbd000000, 0x61000000, 0xc2000000, 0x9f000000, 0x25000000, 0x4a000000, 0x94000000, 0x33000000, 0x66000000, 0xcc000000,
                    0x83000000, 0x1d000000, 0x3a000000, 0x74000000, 0xe8000000, 0xcb000000, 0x8d000000, 0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000, 0x1b000000,
                    0x36000000, 0x6c000000, 0xd8000000, 0xab000000, 0x4d000000, 0x9a000000, 0x2f000000, 0x5e000000, 0xbc000000, 0x63000000, 0xc6000000, 0x97000000, 0x35000000, 0x6a000000, 0xd4000000, 0xb3000000,
                    0x7d000000, 0xfa000000, 0xef000000, 0xc5000000, 0x91000000, 0x39000000, 0x72000000, 0xe4000000, 0xd3000000, 0xbd000000, 0x61000000, 0xc2000000, 0x9f000000, 0x25000000, 0x4a000000, 0x94000000,
                    0x33000000, 0x66000000, 0xcc000000, 0x83000000, 0x1d000000, 0x3a000000, 0x74000000, 0xe8000000, 0xcb000000, 0x8d000000, 0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000,
                    0x40000000, 0x80000000, 0x1b000000, 0x36000000, 0x6c000000, 0xd8000000, 0xab000000, 0x4d000000, 0x9a000000, 0x2f000000, 0x5e000000, 0xbc000000, 0x63000000, 0xc6000000, 0x97000000, 0x35000000,
                    0x6a000000, 0xd4000000, 0xb3000000, 0x7d000000, 0xfa000000, 0xef000000, 0xc5000000, 0x91000000, 0x39000000, 0x72000000, 0xe4000000, 0xd3000000, 0xbd000000, 0x61000000, 0xc2000000, 0x9f000000,
                    0x25000000, 0x4a000000, 0x94000000, 0x33000000, 0x66000000, 0xcc000000, 0x83000000, 0x1d000000, 0x3a000000, 0x74000000, 0xe8000000, 0xcb000000, 0x8d000000, 0x01000000, 0x02000000, 0x04000000,
                    0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000, 0x1b000000, 0x36000000, 0x6c000000, 0xd8000000, 0xab000000, 0x4d000000, 0x9a000000, 0x2f000000, 0x5e000000, 0xbc000000, 0x63000000,
                    0xc6000000, 0x97000000, 0x35000000, 0x6a000000, 0xd4000000, 0xb3000000, 0x7d000000, 0xfa000000, 0xef000000, 0xc5000000, 0x91000000, 0x39000000, 0x72000000, 0xe4000000, 0xd3000000, 0xbd000000,
                    0x61000000, 0xc2000000, 0x9f000000, 0x25000000, 0x4a000000, 0x94000000, 0x33000000, 0x66000000, 0xcc000000, 0x83000000, 0x1d000000, 0x3a000000, 0x74000000, 0xe8000000, 0xcb000000
            };

    private static final byte GetB0(int A) { return (byte)( A      & 0x0FF); }
    private static final byte GetB1(int A) { return (byte)((A>> 8) & 0x0FF); }
    private static final byte GetB2(int A) { return (byte)((A>>16) & 0x0FF); }
    private static final byte GetB3(int A) { return (byte)((A>>24) & 0x0FF); }

    private byte state[][] = new byte[4][4];

    private static int Byte2Word(byte[] src, int src_offset)
    {
        return ((src[src_offset] & 0x0FF) << 24) | ((src[src_offset + 1] & 0x0FF) << 16) | ((src[src_offset + 2] & 0x0FF) << 8) | ((src[src_offset + 3] & 0x0FF));
    }


    private static int RotWord(int w)
    {
        byte b[] = new byte[4];

        b[0] = GetB3(w);
        b[1] = GetB2(w);
        b[2] = GetB1(w);
        b[3] = GetB0(w);


        return ((b[1] & 0x0FF) << 24) | ((b[2] & 0x0FF) << 16) | ((b[3] & 0x0FF) << 8) | (b[0] & 0x0FF);
    }

    private static int SubWord(int w)
    {
        byte b[] = new byte[4];

        b[0] = SBOX[GetB3(w) & 0x0FF];
        b[1] = SBOX[GetB2(w) & 0x0FF];
        b[2] = SBOX[GetB1(w) & 0x0FF];
        b[3] = SBOX[GetB0(w) & 0x0FF];

        return ((b[0] & 0x0FF) << 24) | ((b[1] & 0x0FF) << 16) | ((b[2] & 0x0FF) << 8) | (b[3] & 0x0FF);
    }

    private void AddRoundKey(int[] rKey, int round)
    {
        int i = 0;
        for(i = 0; i < 4; ++i)
        {
            state[0][i] ^= GetB3(rKey[round * NB + i]) & 0x0FF;
            state[1][i] ^= GetB2(rKey[round * NB + i]) & 0x0FF;
            state[2][i] ^= GetB1(rKey[round * NB + i]) & 0x0FF;
            state[3][i] ^= GetB0(rKey[round * NB + i]) & 0x0FF;
        }
    }

    private void SubBytes()
    {
        int i = 0, j = 0;
        for(i = 0; i < 4; ++i)
        {
            for(j = 0; j < 4; ++j)
            {
                state[i][j] = SBOX[state[i][j] & 0x0FF];
            }
        }
    }

    private void ShiftRows()
    {
        byte temp;

        temp = state[1][0];
        state[1][0] = state[1][1];
        state[1][1] = state[1][2];
        state[1][2] = state[1][3];
        state[1][3] = temp;

        temp = state[2][0];
        state[2][0] = state[2][2];
        state[2][2] = temp;
        temp = state[2][1];
        state[2][1] = state[2][3];
        state[2][3] = temp;

        temp = state[3][0];
        state[3][0] = state[3][3];
        state[3][3] = state[3][2];
        state[3][2] = state[3][1];
        state[3][1] = temp;
    }

    private static byte xtime(byte x)
    {
        return (byte)((x<<1) ^ (((x>>7) & 1) * 0x1b));
    }

    private static byte Multiply(byte x, byte y)
    {
        return (byte)(((y & 1) * x) ^ ((y>>1 & 1) * xtime(x)) ^ ((y>>2 & 1) * xtime(xtime(x))) ^ ((y>>3 & 1) * xtime(xtime(xtime(x)))) ^ ((y>>4 & 1) * xtime(xtime(xtime(xtime(x))))));
    }

    private void MixColumns()
    {
        byte Tmp, Tm, t;
        int i;

        for(i = 0; i < 4; ++i)
        {
            t = state[0][i];
            Tmp = (byte)(state[0][i] ^ state[1][i] ^ state[2][i] ^ state[3][i]);
            Tm = (byte)(state[0][i] ^ state[1][i]); Tm = xtime(Tm); state[0][i] ^= Tm ^ Tmp;
            Tm = (byte)(state[1][i] ^ state[2][i]); Tm = xtime(Tm); state[1][i] ^= Tm ^ Tmp;
            Tm = (byte)(state[2][i] ^ state[3][i]); Tm = xtime(Tm); state[2][i] ^= Tm ^ Tmp;
            Tm = (byte)(state[3][i] ^ t); Tm = xtime(Tm); state[3][i] ^= Tm ^ Tmp;
        }
    }

    private void InvSubBytes()
    {
        int i = 0, j = 0;
        for(i = 0; i < 4; ++i)
        {
            for(j = 0; j < 4; ++j)
            {
                state[i][j] = RSBOX[state[i][j] & 0x0FF];
            }
        }
    }

    private void InvShiftRows()
    {
        byte temp;

        temp = state[1][3];
        state[1][3] = state[1][2];
        state[1][2] = state[1][1];
        state[1][1] = state[1][0];
        state[1][0] = temp;

        temp = state[2][0];
        state[2][0] = state[2][2];
        state[2][2] = temp;

        temp = state[2][1];
        state[2][1] = state[2][3];
        state[2][3] = temp;

        temp = state[3][0];
        state[3][0] = state[3][1];
        state[3][1] = state[3][2];
        state[3][2] = state[3][3];
        state[3][3] = temp;
    }

    private void InvMixColumns()
    {
        byte a, b, c, d;
        int i = 0, j = 0;

        for(i = 0; i < 4; ++i)
        {
            a = state[0][i];
            b = state[1][i];
            c = state[2][i];
            d = state[3][i];

            state[0][i] = (byte)(Multiply(a, (byte)0x0E) ^ Multiply(b, (byte)0x0B) ^ Multiply(c, (byte)0x0D) ^ Multiply(d, (byte)0x09));
            state[1][i] = (byte)(Multiply(a, (byte)0x09) ^ Multiply(b, (byte)0x0E) ^ Multiply(c, (byte)0x0B) ^ Multiply(d, (byte)0x0D));
            state[2][i] = (byte)(Multiply(a, (byte)0x0D) ^ Multiply(b, (byte)0x09) ^ Multiply(c, (byte)0x0E) ^ Multiply(d, (byte)0x0B));
            state[3][i] = (byte)(Multiply(a, (byte)0x0B) ^ Multiply(b, (byte)0x0D) ^ Multiply(c, (byte)0x09) ^ Multiply(d, (byte)0x0E));
        }
    }

    public void AES_KeySched(byte[] mKey, int[] rKey)
    {
        int i = 0;
        for (i = 0; i < NK; ++i)
        {
            rKey[i] = Byte2Word(mKey, i << 2);
        }

        while(i < (NB * (NR + 1)))
        {
            int temp = rKey[i-1];

            if(i % NK == 0)
            {
                int rotWord = RotWord(temp);
                temp = SubWord(rotWord) ^ Rcon[i / NK];
            }
            else if(i % NK == 4)
            {
                temp = SubWord(temp);
            }

            rKey[i] = rKey[i - NK] ^ temp;
            i++;
        }
    }

    public void AES_Encrypt(int[] pOut, int[] pIn, int[] rKey)
    {
        int i, j, round = 0;

        for(i = 0; i < 4; ++i)
        {
            state[0][i] = (byte)(GetB3(pIn[i]) & 0x0FF);
            state[1][i] = (byte)(GetB2(pIn[i]) & 0x0FF);
            state[2][i] = (byte)(GetB1(pIn[i]) & 0x0FF);
            state[3][i] = (byte)(GetB0(pIn[i]) & 0x0FF);

        }

        AddRoundKey(rKey, 0);
        for(round = 1; round < NR; ++round)
        {
            SubBytes();
            ShiftRows();
            MixColumns();
            AddRoundKey(rKey, round);
        }

        SubBytes();
        ShiftRows();
        AddRoundKey(rKey, NR);

        for(i = 0; i < 4; ++i)
        {
            int ret = 0;
            for(j = 0; j < 4; ++j)
            {
                ret |= (state[j][i] & 0xFF) << (24-8*j);
            }

            pOut[i] = ret;
        }
    }

    public void AES_Decrypt(int[] pOut, int[] pIn, int[] rKey)
    {
        int i, j, round = 0;

        for(i = 0; i < 4; ++i)
        {
            state[0][i] = (byte)(GetB3(pIn[i]) & 0x0FF);
            state[1][i] = (byte)(GetB2(pIn[i]) & 0x0FF);
            state[2][i] = (byte)(GetB1(pIn[i]) & 0x0FF);
            state[3][i] = (byte)(GetB0(pIn[i]) & 0x0FF);

        }

        AddRoundKey(rKey, NR);
        for(round = NR - 1; round > 0; --round)
        {
            InvShiftRows();
            InvSubBytes();
            AddRoundKey(rKey, round);
            InvMixColumns();
        }

        InvShiftRows();
        InvSubBytes();
        AddRoundKey(rKey, 0);

        for(i = 0; i < 4; ++i)
        {
            int ret = 0;
            for(j = 0; j < 4; ++j)
            {
                ret |= (state[j][i] & 0xFF) << (24-8*j);
            }

            pOut[i] = ret;
        }
    }
}