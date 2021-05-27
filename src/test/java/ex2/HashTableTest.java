package ex2;

import ex2.HashTable;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class HashTableTest {

    @org.junit.jupiter.api.Test
    void hashtable_buida() {
        HashTable ht = new HashTable();

        Assertions.assertEquals(0, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hashtable_no_colissio_1_element() {
        HashTable ht = new HashTable();
        ht.put("1", "a");

        Assertions.assertEquals(1, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hashtable_no_colissio_2_element() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        ArrayList<String> keys = ht.getCollisionsForKey("1", 5);


        Assertions.assertEquals(2, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("bucket[1] = [1, a]\n" +
                "bucket[2] = [2, b]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hashtable_colissio_element() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, b]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hashtable_update_colissio() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");

        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");

        //update amb colissio
        ht.put("1", "av2");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, av2] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, bv2]", ht.toString());
    }
    @org.junit.jupiter.api.Test
    void hastable_delete_no_colissio() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");
         //sin colision

        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");
        ht.drop("2");

        //update amb colissio
        ht.put("1", "av2");


        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, av2] -> [01, c] -> [12, d]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hastable_delete_amb_colissio() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");

        //update amb colissio
        ht.put("1", "av2");

        //delete con primera colision
        ht.drop("1");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [01, c] -> [12, d]\n" +
                " bucket[2] = [2, bv2]", ht.toString());
    }
    @org.junit.jupiter.api.Test
    void hastable_delete_amb_colissio_2() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");

        //update amb colissio
        ht.put("1", "av2");

        //delete con primera colision
        ht.drop("01");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, av2] -> [12, d]\n" +
                " bucket[2] = [2, bv2]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hastable_delete_amb_colissio_3() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");

        //update amb colissio
        ht.put("1", "av2");

        //delete con primera colision
        ht.drop("12");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, av2] -> [01, c]\n" +
                " bucket[2] = [2, bv2]", ht.toString());
    }

    @org.junit.jupiter.api.Test
    void hastable_delete_no_existe() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2", "b");


        //Colisiones
        ht.put("01", "c");
        ht.put("12", "d");

        //update sense colissio
        ht.put("2", "bv2");

        //update amb colissio
        ht.put("1", "av2");
        //delete key inventada
        ht.drop("3");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, av2] -> [01, c] -> [12, d]\n" +
                " bucket[2] = [2, bv2]", ht.toString());
    }
    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_retorn() {
        HashTable ht = new HashTable();
        for(int i=0; i<30; i++) {
            final String key = String.valueOf(i);
            ht.put(key, key);
        }

        Assertions.assertEquals("0", ht.get("0"));
        Assertions.assertEquals("11", ht.get("11"));
        ht.drop("11");
        Assertions.assertNull(ht.get("11"));
        Assertions.assertEquals("9", ht.get("9"));
        Assertions.assertEquals("21", ht.get("21"));
        Assertions.assertEquals("\n" +
                " bucket[0] = [0, 0] -> [22, 22]\n" +
                " bucket[1] = [1, 1] -> [12, 12] -> [23, 23]\n" +
                " bucket[2] = [2, 2] -> [13, 13] -> [24, 24]\n" +
                " bucket[3] = [3, 3] -> [14, 14] -> [25, 25]\n" +
                " bucket[4] = [4, 4] -> [15, 15] -> [26, 26]\n" +
                " bucket[5] = [5, 5] -> [16, 16] -> [27, 27]\n" +
                " bucket[6] = [6, 6] -> [17, 17] -> [28, 28]\n" +
                " bucket[7] = [7, 7] -> [18, 18] -> [29, 29]\n" +
                " bucket[8] = [8, 8] -> [19, 19]\n" +
                " bucket[9] = [9, 9]\n" +
                " bucket[14] = [20, 20]\n" +
                " bucket[15] = [10, 10] -> [21, 21]", ht.toString());
    }
}