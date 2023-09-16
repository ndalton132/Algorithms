import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static class ReturnTwo{
        private long count;
        private int[] sortedArray;

        public ReturnTwo(long count, int[] sortedArray) {
            this.count = count;
            this.sortedArray = sortedArray;
        }





        public long getCount(){
            return count;
        }
         public int[] getSortedArray(){
             return sortedArray;
         }

    }
    public static int[] readFromFile() {

        try {

            File myObj = new File("C:\\Users\\nickd\\Downloads\\algo1.txt");

            Scanner myReader = new Scanner(myObj);
            List<Integer> list = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                 int num = Integer.parseInt(data);
                 list.add(num);
                 
            }
            myReader.close();
            int[] arr = list.stream().mapToInt(i -> i).toArray();
            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        return new int[0];
    }
    public static void main(String[] args) {
        int[] FileRead = (readFromFile());
        System.out.println(FileRead[0]);

        int[] unimodalArray1 = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int[] unimodalArray2 = {10, 20, 30, 40, 50, 40, 30};
        int[] unimodalArray3 = {2, 4, 6, 8, 10, 8, 6, 4, 2};
        int[] unimodalArray4 = {3, 6, 9, 12, 15, 18, 15, 12};
        int[] unimodalArray5 = {1, 3, 5, 7, 9, 11, 13, 11};
        int[] inversions = {5,4,0,4,4,1,4,1,0,8,7,9,2,9,4,2,9,6,4,9,2,5,2,6,0,6,0,6,0,2,9,9,5,5,3,7,7,7,4,9,6,8,9,9,0,8,3};
        int[] inversions1 = {2,4,1,6,8,5,3,7};
        int[] inversions2 = {54044, 1410,  7929,  2964,  2526,  6066,  299,  5377,  4968,  9083};
        int[] inversions3 = {10,9,8,7,6,20,30,50,49};
        //findMax(0,unimodalArray1.length-1,unimodalArray1);
        sortAndCountInv(inversions3);


    }

    public static int findMax(int beg, int end, int [] arr){
        int len = arr.length;
        if(end - beg <= 1){
            return arr[beg];
        }else{
            int mid = len/2;
            int max = Math.max(findMax(beg,len/2,arr),findMax((len/2)+1,end,arr));
            return max;
        }

    }

    //take array and count inversions
    public static ReturnTwo mergeAndCountSplit(ReturnTwo leftArr,ReturnTwo rightArr, int[] arr ){

         int[] left = leftArr.getSortedArray();
         int[] right = rightArr.getSortedArray();
        //left
        int L = 0;
        //right
        int R = 0;

        int S = 0;

        int count = 0;
       
        while(L<left.length && R<right.length){
        if(left[L]<=right[R]){
            arr[S]=left[L];
            L++;
        }else{
            arr[S]=right[R];
            R++;
            //System.out.println(arr.length-1);
            count += ((arr.length)/2) - L;
        }S++;

        }

        while(L<left.length){
            arr[S]=left[L];
            L++;
            S++;

        }while(R<right.length){
            arr[S]=right[R];
            R++;
            S++;

        }
        //System.out.println(count + " : " + Arrays.toString(arr));
        return new ReturnTwo(count, arr);
     }




    public static ReturnTwo sortAndCountInv(int[] arr){

        int len = arr.length;
        System.out.println("");
        int mid = len/2;
        //int count;

        if(len <= 1){
            return new ReturnTwo(0,arr);
        }else{
             int[] left = Arrays.copyOfRange(arr,0, mid);
             int[] right = Arrays.copyOfRange(arr,mid,len);

             ReturnTwo rightArr = sortAndCountInv(right);
             ReturnTwo leftArr = sortAndCountInv(left);
             ReturnTwo result= mergeAndCountSplit(leftArr,rightArr,arr);

             //Reports Sorted array and inv count
             System.out.println(result.getCount()+leftArr.getCount()+ rightArr.getCount() + " : " + Arrays.toString(result.getSortedArray()));
             //Reports only the count
             //System.out.println(result.getCount()+leftArr.getCount()+ rightArr.getCount() + " : " /*Arrays.toString(result.getSortedArray()*/);

             return new ReturnTwo(rightArr.getCount()+ leftArr.getCount()+ result.getCount(), result.getSortedArray());
        }

    }

}
