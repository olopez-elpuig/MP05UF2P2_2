package ex2;

import ex2.HashTable;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class HashTableTest {

    @org.junit.jupiter.api.Test
    void hashtable_1buida() {
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
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a]\n" +
                " bucket[2] = [2, b]", ht.toString());
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
    void hastable_get_comprovar_buida() {
        HashTable ht = new HashTable();



        Assertions.assertNull( ht.get("1"));
    }

    @org.junit.jupiter.api.Test
    void hastable_get_comprovar() {
        HashTable ht = new HashTable();
        ht.put("1", "a");



        Assertions.assertEquals( "a" ,ht.get("1"));
    }

    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_posicio_buida() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");



        Assertions.assertEquals( "b" ,ht.get("2"));
    }

    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_colissio_1() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");

        //Element colissio
        ht.put("01", "c");
        ht.put("12", "d");


        Assertions.assertEquals( "a" ,ht.get("1"));
    }

    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_colissio_2() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");

        //Element colissio
        ht.put("01", "c");
        ht.put("12", "d");

        Assertions.assertEquals( "c" ,ht.get("01"));
    }

    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_colissio_3() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");

        //Element colissio
        ht.put("01", "c");
        ht.put("12", "d");

        Assertions.assertEquals( "d" ,ht.get("12"));
    }
    @org.junit.jupiter.api.Test
    void hastable_get_comprovar_colissio_3elemnts_noexist() {
        HashTable ht = new HashTable();
        ht.put("1", "a");
        ht.put("2","b");

        //Element colissio
        ht.put("01", "c");
        ht.put("12", "d");
        //Este elemento no existe pero esta colisionado por los tres elementos del bucket [1]
        ht.put("23", "e");

        Assertions.assertEquals( "e" ,ht.get("23"));
    }
}