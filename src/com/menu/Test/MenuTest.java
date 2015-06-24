package com.menu.Test;

import com.menu.Dish;
import com.menu.DishRepository;
import com.menu.Menu;
import com.menu.ProductRepository;
import junit.framework.Assert;

import java.util.ArrayList;


/**
 * Created by Valdek on 20.06.2015.
 */
public class MenuTest {


    @org.junit.Test
    public void testAddMenu() throws Exception {
        Menu menu=new Menu();
        DishRepository drep=new DishRepository();
        ArrayList<Dish> expected = new ArrayList<Dish>();
        ArrayList<Dish> actual = new ArrayList<Dish>();
        String[] str={"Borsh","Soup"};
        expected.add(drep.getDishByName("Borsh"));
        expected.add(drep.getDishByName("Soup"));
        actual.addAll(menu.addMenu("MenuTest", str, drep));

        Assert.assertEquals("Error in addMenu",expected,actual);
    }


    @org.junit.Test
    public void testComplexDinner() throws Exception {
        Menu menu=new Menu();
        ProductRepository pr = new ProductRepository();
        DishRepository dr = new DishRepository(pr);
        ArrayList<Dish[]> actual= new ArrayList<>();
        ArrayList<Dish[]> expected= new ArrayList<>();
        Dish[] dishTemp = new Dish[3];
        Dish[] dishTempactual = new Dish[3];
        dishTemp[0]=dr.getDishByName("Juice");
        dishTemp[1]=dr.getDishByName("Borsh");
        dishTemp[2]=dr.getDishByName("Pizza");

        dishTempactual=expected.get(0);
        actual.addAll(menu.complexDinner(400,dr));
       Assert.assertEquals("error",dishTempactual,dishTemp);


    }
}