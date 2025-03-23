import java.util.Scanner;

public class MergeSort{
  public static void main(String[] args){ //메인함수
    int[] array = getInputArray();
    
    mergeSort(array, 0, array.length-1); 
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

  public static void mergeSort(int[] arr, int left, int right){ //배열분할함수
    if (left < right){ 
      int mid = (left + right) / 2; //배열을 반으로 나눌 기준

      mergeSort(arr, left, mid); //왼쪽을 반복해서 나눔
      mergeSort(arr, mid+1, right); //오른쪽을 반복해서 나눔

      merge(arr, left, mid, right); 
      }
  }
  
  public static void merge(int[] arr, int left, int mid, int right){ //병합함수
    //왼쪽, 오른쪽 배열크기
    int n1 = mid - left +1;
    int n2 = right - mid;
    //임시 배열
    int[] L = new int[n1];
    int[] R = new int[n2];

    //왼쪽, 오른쪽 임시배열에 값 복사
    for(int i = 0; i < n1; i++) L[i] = arr[left + i];
    for(int j = 0; j < n2; j++) R[j] = arr[mid + 1 +j];

    //왼쪽, 오른쪽 배열을 비교하면서 순서대로 병합
    int i = 0, j = 0, k=left;
    while(i < n1 && j < n2){
      if(L[i] <= R[j]){
        arr[k++] = L[i++];
        }
      else{ 
        arr[k++] = R[j++];
      }
    }
    //남은요소 복사
    while(i<n1) arr[k++] = L[i++];
    while(j<n2) arr[k++] = R[j++];
  }



  public static void printArray(int[] arr){ //출력함수
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i]);
      if(i != arr.length-1){
        System.out.print(", ");
      }
    }
    System.out.println();
  }
}
