import React, { useEffect, useState } from "react";
import { InputTask } from "./InputTask";
import { GetList } from "./GetList";
import { AddList } from "./AddList";

function State() {
  const [list, setList] = useState([]);
  const [inputText, setText] = useState("");
  const [keyPressed, updateKey] = useState("");

  useEffect(() => {
    GetList().then((result) => setList(result));
  }, []);

  async function addItem(newItem) {
    await AddList(newItem);
    const list = await GetList();
    setList(list);
  }

  return (
    <InputTask
      list={list}
      addItem={addItem}
      inputText={inputText}
      setText={setText}
      keyPressed={keyPressed}
      updateKey={updateKey}
    />
  );
}

export { State };
