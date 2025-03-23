import java.util.Scanner;

public class MergeSort{
  public static void main(String[] args){
    int[] array = getInputArray();
    
    mergeSort(array, 0, array.length-1);
    printArray(array);
  }

  public static int[] getInputArray(){
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

  public static void mergeSort(int[] arr, int left, int right){
    if (left < right){
      int mid = (left + right) / 2;

      mergeSort(arr, left, mid);
      mergeSort(arr, mid+1, right);

      merge(arr, left, mid, right);
      }
  }
  
  public static void merge(int[] arr, int left, int mid, int right){
    int n1 = mid - left +1;
    int n2 = right - mid;
    int[] L = new int[n1];
    int[] R = new int[n2];

    for(int i = 0; i < n1; i++) L[i] = arr[left + i];
    for(int j = 0; j < n2; j++) R[j] = arr[mid + 1 +j];

    int i = 0, j = 0, k=left;
    while(i < n1 && j < n2){
      if(L[i] <= R[j]){
        arr[k++] = L[i++];
        }
      else{ 
        arr[k++] = R[j++];
      }
    }
    while(i<n1) arr[k++] = L[i++];
    while(i<n2) arr[k++] = R[j++];
  }



  public static void printArray(int[] arr){
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i]);
      if(i != arr.length-1){
        System.out.print(", ");
      }
    }
  }

}
