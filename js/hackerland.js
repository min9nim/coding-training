function hackerlandRadioTransmitters(x, k) {
    var n = x.length;
    arr = x.sort(function(a,b){return Number(a)-Number(b);});
    radio = x.map(()=>0);
}
function tmp(i, k){
    //i번째 요소에 radio 설치를 해야할 지 여부

    //  단순 거리 조건으로 체크
    if(i===0){
        if(arr[1] - arr[0] > k){
            return true;
        }else{
            return false;
        }
    }

    if(i === arr.length-1){
        if(arr[i] - arr[i-1] > k){
            return true;
        }
    }

    if( arr[i] - arr[i-1] > k
    &&  arr[i+1] - arr[i] > k
    ){
        return true;
    }


    //이웃한 곳의 라디오 설치여부에 따라
    for(var ix=0; ix++; ix<k){
        if(radio[i-ix-1]){
            return false;
        }
    }
    return true;




}
hackerlandRadioTransmitters([7, 2, 4, 6, 5, 9, 12, 11], 2);
