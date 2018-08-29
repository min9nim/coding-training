function swap(arr, i1, i2){
    var tmp = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = tmp;
}

function log(pivot, left, right, a){
    var head = "x".repeat(left).split("");
    var body = a.slice(left, right+1);
    var tail = "x".repeat(a.length-1-right).split("");
    console.log("p(" + pivot + ") => " + [...head, ...body, ...tail]);    
}

function quickSort(a, left=0, right=a.length-1){
    if(left >= right){
        return;
    }
    var pivot = a[left];   // 첫번째 요소를 pivot으로 세팅
    var i = left;
    var j = right;

    while(i < j){
        while(pivot < a[j]) j--;    // 오른쪽끝부터 왼쪽으로 pivot 보다 작거나 같은 값의 위치를 찾는다
        while(i<j && a[i] <= pivot) i++;    // 왼쪽끝부터 오른쪽으로 pivot보다 큰값의 위치를 찾는다
        swap(a, i, j);      // 두 값을 교환
    }
    swap(a, left, i);   // swap 결과 i 위치를 기준으로 왼쪽은 모두 pivot 보다 작거나 같은 값이 되고, 오른쪽은 pivot보다 큰 값이 위치하게 된다

    log(pivot, left, right, a); // pivot 으로 양분된 결과 출력

    quickSort(a, left, i-1);
    quickSort(a, i+1, right);
}
var arr = [4,5,7,5,4,2,6,0,8,3,1,3,9];
quickSort(arr);
console.log(arr);
/*
p(4) => 0,3,1,3,4,2,4,6,8,5,7,5,9
p(0) => 0,3,1,3,4,2,x,x,x,x,x,x,x
p(3) => x,2,1,3,3,4,x,x,x,x,x,x,x
p(2) => x,1,2,3,x,x,x,x,x,x,x,x,x
p(6) => x,x,x,x,x,x,x,5,5,6,7,8,9
p(5) => x,x,x,x,x,x,x,5,5,x,x,x,x
p(7) => x,x,x,x,x,x,x,x,x,x,7,8,9
p(8) => x,x,x,x,x,x,x,x,x,x,x,8,9
[0, 1, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9]
*/