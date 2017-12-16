function avoidObstacles(inputArray) {
  var i = 2;
  while(true){
    if(inputArray.every(x => x%i != 0)){
      return i;
    }
    i++;
  }
}

console.log(avoidObstacles([1,5,7,8]))
