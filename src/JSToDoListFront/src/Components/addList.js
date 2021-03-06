function addList(listItem) {
  return fetch("/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: [JSON.stringify({ name: listItem })],
  }).then((res) => {
    if (res.ok) {
      console.log("HTTP request successful");
    } else {
      console.log("HTTP request unsuccessful.");
    }
  });
}

export { addList };
