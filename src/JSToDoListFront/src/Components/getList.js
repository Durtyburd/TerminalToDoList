async function getList() {
  const response = await fetch("/list");
  const data = await response.json();
  const arr = data.map((data) => data.name);
  return arr;
}

export { getList };
