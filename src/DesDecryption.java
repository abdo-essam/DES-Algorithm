// Data Encryption Standard (Block Cipher method)

import java.util.HashMap;
import java.util.Scanner;

public class DesDecryption {

    public static String hexToBinary(String hex)
    {

        String binary = "";
        hex = hex.toUpperCase();
        HashMap<Character, String> hashMap = new HashMap<Character, String>();
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }

    public static String binaryToHex(String binary) {

        HashMap<String, Character> hashMap = new HashMap<String, Character>();
        hashMap.put("0000", '0');
        hashMap.put("0001", '1');
        hashMap.put("0010", '2');
        hashMap.put("0011", '3');
        hashMap.put("0100", '4');
        hashMap.put("0101", '5');
        hashMap.put("0110", '6');
        hashMap.put("0111", '7');
        hashMap.put("1000", '8');
        hashMap.put("1001", '9');
        hashMap.put("1010", 'A');
        hashMap.put("1011", 'B');
        hashMap.put("1100", 'C');
        hashMap.put("1101", 'D');
        hashMap.put("1110", 'E');
        hashMap.put("1111", 'F');


        String bin;
        String hex = "";

        for (int i = 0; i < binary.length(); i = i + 4) {
            bin = binary.substring(i, i + 4);
            if (hashMap.containsKey(bin))
                hex += hashMap.get(bin);
            else {
                hex = "Invalid Hexadecimal String";
                return hex;
            }
        }
        return hex;
    }



    // function that rotates data towards left by s
    public static int[] leftRotate(int[] data, int s) {

        int index = 0;
        int[] ans = new int[28];
        for (int i = s; i < data.length; i++) {
            ans[index] = data[i];
            index++;
        }
        for (int i = 0; i < s; i++) {
            ans[index] = data[i];
            index++;
        }
        return ans;
    }


    public static String decimalToBinary(int dec) {
        String bin = Integer.toBinaryString(dec);
        String zero = "";
        if (bin.length() < 4) {
            for (int i = 0; i < (4 - bin.length()); i++) {
                zero += '0';
            }
        }
        return (zero + bin);
    }


    static int binaryToDecimal(String binaryString) {

        int decimal = Integer.parseInt(binaryString, 2);
        return decimal;
    }

    static String sBox(String KER) {
        // S boxes 3 Dimensional (box,row,column)
        final int[][][] S = {{
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        }, {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        }, {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        }, {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        }, {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        }, {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        }, {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        }, {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }};

        String data = "", row = "", column = "";
        String sBoxValues = "";
        int box = 0;
        for (int i = 0; i < 48; i += 6) {
            data = KER.substring(i, i + 6);
            row = data.charAt(0) + "" + data.charAt(5);
            column = data.substring(1, 5);
            //System.out.println();
            //System.out.println("row : " + row + " colum : " + column);
            //System.out.println("element :" + decimalToBinary(S[binaryToDecimal(row)][binaryToDecimal(column)]));
            sBoxValues += decimalToBinary(S[box][binaryToDecimal(row)][binaryToDecimal(column)]);
            ++box;
        }
        return sBoxValues;
    }


    public static void main(String[] args) {
        // Key is 4 bit
        int[] Key = new int[64];


        /**Key Generation*/

        // String k = "0001001100110100010101110111100110011011101111001101111111110001" ;
         System.out.print("Enter the Key :");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = hexToBinary(input);
        // String input = "0001001100110100010101110111100110011011101111001101111111110001";

        for (int i = 0; i < input.length(); ++i) {
            Key[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }

        /**
         * PC1 Permutation Choice.  The supplied 64-bit key is permuted according
         * to this table into a 56-bit key.
         *
         */

        // contain the indexes
        final int[] PC1 = {
                57, 49, 41, 33, 25, 17, 9,
                1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14, 6, 61, 53, 45, 37, 29,
                21, 13, 5, 28, 20, 12, 4
        };

        int[] PKey = new int[56];

        for (int i = 0; i < PC1.length; ++i) {
            PKey[i] = Key[PC1[i] - 1]; // -1 to start from index 0
        }


        int space = 7;

/*        System.out.print("K+ =");

        for (int i = 0; i < PKey.length; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 7;
            }
            --space;
            System.out.print(PKey[i]);
        }
        System.out.println();*/


        int[] C0 = new int[28];
        int[] D0 = new int[28];

        for (int i = 0; i < 28; ++i) {
            C0[i] = PKey[i];
        }

        for (int i = 28; i < 56; ++i) {
            D0[i - 28] = PKey[i]; // to start form 0 index
        }

        int[][] C = new int[16][28]; // contain for C1 to C16
        int[][] D = new int[16][28]; // contain for D1 to D16

        for (int i = 0; i < 16; i++) {

            if (i == 0 || i == 1 || i == 8 || i == 15) {
                C0 = leftRotate(C0, 1); // left shift by 1
                D0 = leftRotate(D0, 1); // left shift by 1
            } else {
                C0 = leftRotate(C0, 2); // left shift by 2
                D0 = leftRotate(D0, 2); // left shift by 2
            }
            C[i] = C0;
            D[i] = D0;

        }
/*
        for (int i = 0; i < 16; i++) {
            System.out.print("C" + (i + 1) + ": ");
            for (int j = 0; j < 28; j++) {
                System.out.print(C[i][j]);
            }
            System.out.println();
            System.out.print("D" + (i + 1) + ": ");
            for (int j = 0; j < 28; j++) {
                System.out.print(D[i][j]);
            }
            System.out.println();
        }
*/


        /**
         * PC2 Permutation.  The subkey generation process applies this
         * permutation to transform its running 56-bit keystuff value into
         * the final set of 16 48-bit subkeys.
         */
        final int[] PC2 = {
                14, 17, 11, 24, 1, 5,
                3, 28, 15, 6, 21, 10,
                23, 19, 12, 4, 26, 8,
                16, 7, 27, 20, 13, 2,
                41, 52, 31, 37, 47, 55,
                30, 40, 51, 45, 33, 48,
                44, 49, 39, 56, 34, 53,
                46, 42, 50, 36, 29, 32
        };

        int[][] K = new int[16][48];// we have 16 Key and each key is 48 bit
        // from K1 to K16
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < PC2.length; j++) {
                // 1 - 28
                if (PC2[j] < 29) {
                    K[i][j] = C[i][PC2[j] - 1];
                } else {
                    // 29 - 56
                    K[i][j] = D[i][PC2[j] - 29];
                }
            }
        }


        space = 6;
        for (int i = 0; i < 16; i++) {
            System.out.print("K" + (i + 1) + ": ");
            for (int j = 0; j < 48; j++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 6;
                }
                --space;
                System.out.print(K[i][j]);
            }
            System.out.println();
        }


        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        /**
         * Here we will work on the Data
         */

        /**
         * Initial Permutation.  The message block is permuted by this
         * permutation at the beginning of the algorithm.
         */

        final int[] IP = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };
        space = 4;

        // String k = "0001001100110100010101110111100110011011101111001101111111110001" ;

        System.out.print("Enter the cypher :");

         input = sc.nextLine();
         input = hexToBinary(input);

       // input = "1000010111101000000100110101010000001111000010101011010000000101" ;

        // System.out.print("Enter Your Data:");
        // input = sc.nextLine();

        // contain the data
        int[] M = new int[64];


        for (int i = 0; i < input.length(); ++i) {
            M[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }

        System.out.print("M = ");
        for (int i = 0; i < M.length; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 4;
            }
            --space;
            System.out.print(M[i]);
        }
        System.out.println();

        // data after Permutation
        int[] PM = new int[64];

        for (int i = 0; i < IP.length; ++i) {
            PM[i] = M[IP[i] - 1]; // -1 to start from index 0
        }

        System.out.print("IP = ");
        for (int i = 0; i < PM.length; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 4;
            }
            --space;
            System.out.print(PM[i]);
        }


        int[][] L = new int[18][32]; // contain for L1 to L16
        int[][] R = new int[18][32]; // contain for R1 to R16


        // for the first R0 and L0
        for (int i = 0; i < 32; i++) {
            L[0][i] = PM[i];
        }

        for (int i = 32; i < 64; i++) {
            R[0][i - 32] = PM[i]; // i - 32 to start from index 0
        }


/*        System.out.println();
        System.out.print("L0: ");
        for (int i = 0; i < 32; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 4;
            }
            --space;
            System.out.print(L[0][i]);
        }

        System.out.println();
        System.out.print("R0: ");
        for (int i = 0; i < 32; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 4;
            }
            --space;
            System.out.print(R[0][i]);
        }*/


        for (int round = 0; round < 16; ++round) {

            for (int i = 0; i < 32; i++) {
                L[round + 1][i] = R[round][i];
            }


            /**
             * Here is Mangler Function (Start)
             */

            /**
             * Expansion Permutation.
             * this permutation to its 32-bit input half-block to create an
             * "expanded" 48-bit value.
             */

            // E Bit-selection table
            final int[] E = {
                    32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1
            };

            System.out.println();
            int[] ER = new int[48];
            for (int i = 0; i < E.length; i++) {
                ER[i] = R[round][E[i] - 1];
            }
            space = 6;


            System.out.print("E(R" + round + ')' + ":\t\t\t");
            for (int i = 0; i < ER.length; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 6;
                }
                --space;
                System.out.print(ER[i]);
            }

            System.out.println();

            // printing the key
            space = 6;
            System.out.print("K(" + ((15-1*round) + 1) + ')' + ":\t\t\t");
            for (int i = 0; i < 48; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 6;
                }
                --space;
                System.out.print(K[round][i]);
            }

            System.out.println();


            String KER = ""; // contain Key xor E(Rn-1)
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println((15-1*round));
            for (int i = 0; i < 48; i++) {
                KER += ER[i] ^ K[(15-1*round)][i];
            }

            space = 6;



            System.out.print("K(" + ((15-1*round) + 1) + ')' + " + " + "E(R" + round + ") =  ");
            for (int i = 0; i < 48; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 6;
                }
                --space;
                System.out.print(KER.charAt(i));
            }

            space = 4;
            String sBoxValues = sBox(KER);
            System.out.println();
            System.out.print("SB: ");
            for (int i = 0; i < sBoxValues.length(); i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 4;
                }
                --space;
                System.out.print(sBoxValues.charAt(i));
            }

            /**
             * "P" Permutation.  The Feistel function concludes by applying this
             * 32-bit permutation to the result of the S-box substitution, in
             * order to spread the output bits across 6 different S-boxes in
             * the next round.
             */
            final int[] P = {
                    16, 7, 20, 21,
                    29, 12, 28, 17,
                    1, 15, 23, 26,
                    5, 18, 31, 10,
                    2, 8, 24, 14,
                    32, 27, 3, 9,
                    19, 13, 30, 6,
                    22, 11, 4, 25
            };

            space = 4;
            int[] f = new int[32];
            System.out.println();
            System.out.print("f=  ");
            for (int i = 0; i < P.length; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 4;
                }
                --space;
                f[i] = Character.getNumericValue(sBoxValues.charAt(P[i] - 1));
                System.out.print(f[i]);
            }


            // Rn+1 = Ln xor f(Rn , Kn+1 )


            for (int i = 0; i < 32; i++) {
                R[round + 1][i] = L[round][i] ^ f[i];
            }


/*
            space = 6 ;
            System.out.print('K' + (round+1) + " + " +"E(R"+round+')');
            for (int i = 0; i < ER.length; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 6;
                }
                --space;
                System.out.print(ER[i]);
            }

*/
            /**
             * Here is Mangler Function (End)
             */

            System.out.println();
            System.out.print("--------------------------------------------------------------------------------------------------------------------------------------");
        }


        // Print all R and L

        for (int round = 0; round < 17; round++) {
            space = 4;
            System.out.println();
            System.out.print("L" + (round) + " = ");
            for (int i = 0; i < 32; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 4;
                }
                --space;
                System.out.print(L[round][i]);
            }

            System.out.println();

            space = 4;
            System.out.print("R" + (round) + " = ");
            for (int i = 0; i < 32; i++) {
                if (space == 0) {
                    System.out.print(' ');
                    space = 4;
                }
                --space;
                System.out.print(R[round][i]);
            }

        }

/*
        // swap L -> R and R -> L
        int swap;
        for (int i = 0; i < 32; i++) {
            swap = R[15][i];
            R[15][i] = L[15][i];
            L[15][i] = swap;
        }
*/




        int[] RL = new int[64] ;

        for (int i = 0; i < 32; i++) {
            RL[i] = R[16][i];
        }

        for (int i = 0; i < 32; i++) {
            RL[i+32] = L[16][i];
        }
        /**
         * Final Permutation.  The final result is permuted by this
         * permutation to generate the final ciphertext block.
         */
        final int[] FP = {
                40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25
        };

        String inverseIP="" ;
        for (int i = 0; i < 64; i++) {
            inverseIP += RL[FP[i] - 1];
        }
        System.out.println();

        space = 4 ;
        System.out.print("IP = ");
        for (int i = 0; i < 64 ; i++) {
            if (space == 0) {
                System.out.print(' ');
                space = 4;
            }
            --space;
            System.out.print(inverseIP.charAt(i));
        }

        System.out.println();
        System.out.println(binaryToHex(inverseIP));


    }
}
