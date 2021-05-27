package ex4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void hashtable_no_colissio(){
        HashTable ht = new HashTable();

        ht.put("1", 1);

        Assertions.assertEquals(1, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hashtable_no_colissio_2(){
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        Assertions.assertEquals(2, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hashtable_colissio(){
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hashtable_get_comprovar_posicio_buida() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);


        Assertions.assertEquals(2, ht.get("2"));
    }

    @Test
    void hastable_get_comprovar_colissio_1(){
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        Assertions.assertEquals(1, ht.get("1"));
    }

    @Test
    void hastable_get_comprovar_colissio_2(){
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        Assertions.assertEquals(3, ht.get("01"));
    }

    @Test
    void hastable_get_comprovar_colissio_3(){
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        Assertions.assertEquals(4, ht.get("12"));
    }

    @Test
    void hastable_delete_no_colissio() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        ht.drop("2");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hastable_delete_colissio_1() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        ht.drop("1");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hastable_delete_colissio_2() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        ht.drop("01");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hastable_delete_colissio_3() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        ht.drop("12");

        Assertions.assertEquals(3, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @Test
    void hastable_delete_no_exist() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("2", 2);

        //colissions
        ht.put("01", 3);
        ht.put("12", 4);

        ht.drop("3");

        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
    }
}