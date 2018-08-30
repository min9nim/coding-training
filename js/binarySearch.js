// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem

function binarySearch(arr, val, s, e){
    if(s === e){
        if(arr[s].val === val){
            return arr[s].id;
        }else{
            return null;
        }
    }else if(s > e){
        return null;
    }
    var c = Math.floor((s+e)/2);
    if(arr[c].val === val){
        return arr[c].id;
    }else if(arr[c].val > val){
        return binarySearch(arr, val, s, c-1);
    }else if(arr[c].val < val){
        return binarySearch(arr, val, c+1, e);
    }
}

function whatFlavors(cost, m){
    var costObj = cost.map((c,i) => {return {val: c, id: i+1};});
    costObj.sort((a,b)=>a.val-b.val);
    for(var i=0; i<costObj.length-1; i++){
        var id2 = binarySearch(costObj, m-costObj[i].val, i+1, costObj.length-1);
        if(id2){
            var id1 = costObj[i].id;
            console.log(Math.min(id1, id2) + " " + Math.max(id1, id2));
            return;
        }
    }
}

var arr = [7, 2, 5, 4, 11]
whatFlavors(arr, 12);
