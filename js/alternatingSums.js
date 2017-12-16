alternatingSums = a => a.reduce((p,v,i) => (p[i&1]+=v,p), [0,0])


console.log(alternatingSums([50, 60, 60, 45, 70]));
