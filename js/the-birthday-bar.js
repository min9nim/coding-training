// https://www.hackerrank.com/challenges/the-birthday-bar/problem

var s = [1, 2, 1, 3, 2];
var d = 3;
var m = 2;

// Complete the birthday function below.
function birthday(s, d, m) {
    var cnt = 0;
    for(var i=0; i<s.length-m+1; i++){
        if(d === s.slice().splice(i, m).reduce((a,c) => a+c)){
            cnt++;
        }
    }
    return cnt;
}

birthday(s,d,m);