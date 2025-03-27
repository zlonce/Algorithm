import java.util.Scanner;
class PivotCount{
  int count;
}

public class QuickPivotComparison{
  public static void main(String[] args){ //메인함수
    int[] array = getInputArray();
    int[] arr1 = array.clone();
    int[] arr2 = array.clone();
    
    PivotCount medianCount = new PivotCount();
    medianPivot(arr1, 0, arr1.length-1, medianCount);
    System.out.println("Median 방식  Pivot 선택 횟수: " + medianCount.count);
    printArray(arr1);

    PivotCount randomCount = new PivotCount();
    randomPivot(arr2, 0, arr2.length-1, randomCount);
    System.out.println("Random 방식 Pivot 선택 횟수: " + randomCount.count);
    printArray(arr2);
  }

  public static int[] getInputArray(){ //배열입력함수
    Scanner sc = new Scanner(System.in);
    System.out.print("배열 크기를 입력해주세요. : ");
    int n = sc.nextInt();

    int[] array = new int[n];
    System.out.print("배열을 입력해주세요. : ");
    for(int i = 0; i<n; i++){
      array[i] = sc.nextInt();
    }
    return array;
  }

  public static void medianPivot(int[] arr, int left, int right, PivotCount count){ 
    if(left < right){
      count.count++;
      int mid = (left + right) / 2;
      if(arr[left]>arr[mid]) swap(arr, left, mid);
		  if(arr[mid]>arr[right]) swap(arr, mid, right);
		  if(arr[left]>arr[mid]) swap(arr, left, mid);
      swap(arr, left, mid);
      
      int i = left + 1;
      int j = right;

      while (i <= j){
        while(i <= right && arr[i] <= arr[left]) i++;
        while(j >= left + 1 && arr[j] > arr[left]) j--;
        if(i < j){
          swap(arr, i, j);
        }
      }

      swap(arr, left, j);

      medianPivot(arr, left, j-1, count);
      medianPivot(arr, j+1, right, count);
    }
  }


  public static void randomPivot(int[] arr, int left, int right, PivotCount count){

    if(left < right){
      count.count++;
      int pivot = left + (int)(Math.random() * (right - left + 1));
      swap(arr, left, pivot);
      
      int i = left + 1;
      int j = right;

      while (i <= j){
        while(i <= right && arr[i] <= arr[left]) i++;
        while(j >= left + 1 && arr[j] > arr[left]) j--;
        if(i < j){
          swap(arr, i, j);
        }
      }

      swap(arr, left, j);

      randomPivot(arr, left, j-1, count);
      randomPivot(arr, j+1, right, count);
    }
  }
  

  public static void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void printArray(int[] arr){ //출력함수
    System.out.print("정렬 결과: ");
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i]);
      if(i != arr.length-1){
        System.out.print(", ");
      }
    }
    System.out.println();
  }
}
  
