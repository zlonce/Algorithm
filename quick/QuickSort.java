import java.util.Scanner;

public class QuickSort{
  public static void main(String[] args){ //메인함수
    int[] array = getInputArray();
    
    quickSort(array, 0, array.length-1); //퀵 정렬 
    printArray(array); //출력함수
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

  public static void quickSort(int[] arr, int left, int right){ //퀵함수
    if(left < right){
      //랜덤으로 pivot 선택 후 가장 왼쪽값과 교환
      int pivot = left + (int)(Math.random() * (right - left + 1));
      swap(arr, left, pivot);
      
      int i = left + 1;
      int j = right;

      while (i <= j){// pivot을 기준으로 분할
        //pivot보다 큰 값을 찾을 때까지 왼쪽으로 이동
        while(i <= right && arr[i] <= arr[left]) i++;
        //pivot보다 작은 값을 찾을 때까지 오른쪽으로 이동
        while(j >= left + 1 && arr[j] > arr[left]) j--; 
        if(i < j){
          swap(arr, i, j);// pivot보다 작은값, 큰값 위치 교환
        }
      }

      swap(arr, left, j); //피벗을 중앙으로 이동

      //왼쪽, 오른쪽 재귀함수로 반복
      quickSort(arr, left, j-1);
      quickSort(arr, j+1, right);
    }
  }
  

  public static void swap(int[] arr, int i, int j){ //배열의 두 요소 교환
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
  
