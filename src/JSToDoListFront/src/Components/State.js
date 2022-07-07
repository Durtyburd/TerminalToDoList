import React, { useEffect, useState } from "react";
import { InputTask } from "./InputTask";
import { getList } from "./getList";
import { addList } from "./addList";
import { deleteList } from "./deleteList";

function State() {
  const [listItem, setList] = useState([]);
  const [inputText, setText] = useState("");
  const [keyPressed, updateKey] = useState("");

  function reload() {
    getList().then((result) => setList(result));
  }

  setInterval(reload, 1000);
  useEffect(reload, []);

  async function addItem(newItem) {
    await addList(newItem);
    const list = await getList();
    setList(list);
  }

  async function deleteItem(item) {
    await deleteList(item);
    const list = await getList();
    setList(list);
  }

  return (
    <InputTask
      deleteItem={deleteItem}
      listItem={listItem}
      addItem={addItem}
      inputText={inputText}
      setText={setText}
      keyPressed={keyPressed}
      updateKey={updateKey}
    />
  );
}

export { State };
