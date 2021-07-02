package com.sumeet.algovisualizer.model;

import androidx.lifecycle.MutableLiveData;

import org.eazegraph.lib.models.BarModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import kotlin.jvm.internal.markers.KMutableList;

public class SelectionSort {

    private List<BarModel> list;
    private int n;

    public SelectionSort(List<BarModel> list){
        this.list = list;
        n = list.size();
    }

    public List<BarModel> getList() {
        return list;
    }

    public void sortOneStep(){

        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (list.get(j).getValue() < list.get(min_idx).getValue())
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
//            int temp = arr[min_idx];
//            arr[min_idx] = arr[i];
//            arr[i] = temp;

            swap(min_idx,i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void swap(int x, int y){

        BarModel temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);

    }

}