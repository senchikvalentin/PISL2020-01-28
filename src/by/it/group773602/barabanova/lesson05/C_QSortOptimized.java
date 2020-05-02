package by.it.group773602.barabanova.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    private int index1 = 0;
    private int index2 = 0;

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return (o.stop - o.start) - (stop - start);
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        quickSort(segments, 0, segments.length - 1);

        for (Segment segment : segments) {
            System.out.println(segment.start + " " + segment.stop);
        }

        for (int i = 0; i < points.length; i++) {
            int count = 0;

            int left = 0;
            int right = segments.length - 1;

            boolean stop = false;

            while (left <= right && !stop) {
                int mid = (left + right) / 2;
                if (points[i] <= segments[mid].stop && points[i] >= segments[mid].start) {
                    count++;
                    stop = true;
                } else if (segments[mid].stop < points[i]) {
                    left = mid + 1;
                } else if (segments[mid].stop > points[i]) {
                    right = mid - 1;
                }
            }
            result[i] = count;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort(Segment[] arr, int left, int right) {
        if (left < right) {
            partition(arr, left, right);
            quickSort(arr, left, index2);
            quickSort(arr, index1, right);
        }
    }

    private void partition(Segment[] arr, int left, int right) {
        index1 = left - 1;
        index2 = right;

        int p = left - 1;
        int q = right;

        Segment v = arr[right];
        while (true) {
            while (v.compareTo(arr[++index1]) > 0) ;
            while (v.compareTo(arr[--index2]) >= 0) {
                if (index2 == left) {
                    break;
                }
            }
            if (index1 >= index2) {
                break;
            }
            swap(arr, index1, index2);

            if (arr[index1] == v) {
                p++;
                swap(arr, p, index1);
            }

            if (arr[index2] == v) {
                q--;
                swap(arr, index2, q);
            }
        }
        swap(arr, index1, right);

        index2 = index1 - 1;

        for (int k = left; k < p; k++, index2--) {
            swap(arr, k, index2);
        }

        index1 += 1;

        for (int k = right - 1; k > q; k--, index1++) {
            swap(arr, index1, k);
        }
    }

    private void swap(Segment[] arr, int first, int second) {
        Segment swap = arr[first];
        arr[first] = arr[second];
        arr[second] = swap;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group773602/palto/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
