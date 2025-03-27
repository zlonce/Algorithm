import java.util.Scanner;

public class QuickSort{
  public static void main(String[] args){ //메인함수
    int[] array = getInputArray();
    
    quickSort(array, 0, array.length-1); 
    printArray(array);
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

  public static void quickSort(int[] arr, int left, int right){ 
    if(left < right){
      int pivot = (left + right) / 2;
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

      quickSort(arr, left, j-1);
      quickSort(arr, j+1, right);
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
  
