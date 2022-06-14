package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("sort")
public class SortTest {

    @PostMapping("bubbleSort")
    public int[] bubbleSort(@RequestParam("sourceArray") int[] sourceArray) {
        int length = sourceArray.length;
        for (int i = 1; i < length; i++) {
            boolean flag = true;
            for (int j = 0; j < length - i; j++) {
                if (sourceArray[j] > sourceArray[j + 1]) {
                    int temp = sourceArray[j];
                    sourceArray[j] = sourceArray[j + 1];
                    sourceArray[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return sourceArray;
    }

    /**
     * 选择排序，无论什么数据进去都是 O(n²) 的时间复杂度
     * 数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧
     *
     * @param sourceArray
     * @return
     */
    @PostMapping("selectionSort")
    public int[] selectionSort(@RequestParam("sourceArray") int[] sourceArray) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < sourceArray.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < sourceArray.length; j++) {
                if (sourceArray[j] < sourceArray[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = sourceArray[i];
                sourceArray[i] = sourceArray[min];
                sourceArray[min] = tmp;
            }
        }
        return sourceArray;
    }

    /**
     * optimisation 拆半插入(什么东西)
     *
     * @param sourceArray
     * @return
     */
    @PostMapping("insertionSort")
    public int[] insertionSort(@RequestParam("sourceArray") int[] sourceArray) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < sourceArray.length; i++) {
            // 记录要插入的数据
            int tmp = sourceArray[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < sourceArray[j - 1]) {
                sourceArray[j] = sourceArray[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                sourceArray[j] = tmp;
            }
        }
        return sourceArray;
    }


    /**
     * 计数排序 countingSort
     */
    @PostMapping("countingSort")
    public int[] countingSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int maxValue = getMaxValue(arr);
        return countingSort(arr, maxValue);
    }

    private int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];

        for (int value : arr) {
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }




}
