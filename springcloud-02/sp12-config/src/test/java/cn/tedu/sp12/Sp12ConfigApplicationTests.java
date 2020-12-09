package cn.tedu.sp12;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

//@SpringBootTest
class Sp12ConfigApplicationTests {

    @Test
    void contextLoads() {//冒泡排序
        int[] a={7,9,4,6,8,3,4,2,8};
        for (int i=1;i<a.length;i++){
            for (int j=0;j<a.length-i;j++){
                if (a[j]>a[j+1]){
                    int b=a[j];
                    a[j]=a[j+1];
                    a[j+1]=b;
                }
            }
        }

        System.out.println(Arrays.toString(a));
    }

    @Test
    void test2(){
        
    }
}
