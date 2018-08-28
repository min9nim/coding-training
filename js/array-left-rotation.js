// https://www.hackerrank.com/challenges/array-left-rotation/problem

function main() {
    const n = 5;
    const d = 4;
    const a = [1,2,3,4,5];

    var tmp = a.splice(0,d);
    console.log(a.concat(tmp).join(" "));
}

main();