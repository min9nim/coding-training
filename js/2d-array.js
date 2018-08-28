// https://www.hackerrank.com/challenges/2d-array/problem

var arr = [];
var str = 
`-9 -9 -9 1 1 1 
0 -9 0 4 3 2
-9 -9 -9 1 2 3
0 0 8 6 6 0
0 0 0 -2 0 0
0 0 1 2 4 0`;

str.split("\n").forEach(function(row, idx){
    arr[idx] = row.split(" ").map(Number);
})

// Complete the hourglassSum function below.
function hourglassSum(arr) {
    function sum(row, col){
        var res = 0;
        res += arr[row][col] + arr[row][col+1] + arr[row][col+2];
        res += arr[row+1][col+1];
        res += arr[row+2][col] + arr[row+2][col+1] + arr[row+2][col+2];
    
        return res;
    }

    var arrsum = [];
    for(var i=0; i<4; i++){
        for(var j=0; j<4; j++){
            arrsum.push(sum(i,j));
        }
    }
    return Math.max(...arrsum);
}

hourglassSum(arr)