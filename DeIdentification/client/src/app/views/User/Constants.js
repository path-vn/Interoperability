const ListLevedId = (level) =>{
  let list = [] 
  if(level == 2){
    list =[
      {value:2,name:"Khu Vực"},
      {value:3,name:"Tỉnh"},
      {value:4,name:"Quận/Huyện"},
    ]
  }else if(level == 3){
    list =[
      {value:3,name:"Tỉnh"},
      {value:4,name:"Quận/Huyện"},
    ]
  }else if(level == 4){
    list =[
      {value:4,name:"Quận/Huyện"},
    ]
  }else {
    list = [
      {value:0,name:"--"},
      {value:1,name:"TW"},
      {value:2,name:"Khu Vực"},
      {value:3,name:"Tỉnh"},
      {value:4,name:"Quận/Huyện"},
    ]
  }

  return list
}


const ListLeved =[
    {value:0,name:"--"},
    {value:1,name:"TW"},
    {value:2,name:"Khu Vực"},
    {value:3,name:"Tỉnh"},
    {value:4,name:"Quận/Huyện"},
  ];
  const Lived ={
    NATION:1,
    AREA:2,
    REGION: 2,
    PROVINCE:3,
    FACILITY:4
  }

  module.exports = Object.freeze({
    //ROOT_PATH : "/egret/",
    ListLevedId: ListLevedId,
    ListLeved: ListLeved,
    Lived:Lived,
  });