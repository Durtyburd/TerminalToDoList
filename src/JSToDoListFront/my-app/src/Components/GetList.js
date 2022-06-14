async function GetList(props) {
  let arr = [];
  const response = await fetch("http://localhost:420/list");
  const data = await response.json();
  for (let i = 0; i < data.length; i++) {
    arr.push(data[i].name);
  }
  return arr;
}

export { GetList };
