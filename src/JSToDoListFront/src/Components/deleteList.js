async function deleteList(listItem) {
  return fetch("http://localhost:420/delete", {
    method: "DELETE",
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

export { deleteList };
