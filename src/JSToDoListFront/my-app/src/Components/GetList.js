async function GetList(props) {
  const response = await fetch("http://localhost:420/list");
  const data = await response.json();
  const arr = data.map((data) => data.name);
  return arr;
}

export { GetList };
