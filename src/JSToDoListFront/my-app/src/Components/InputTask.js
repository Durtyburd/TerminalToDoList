import { ListItems } from "./ListItems";
import React from "react";

function InputTask({ list, addItem, inputText, setText }) {
  const items = [];

  const handleChange = (e) => {
    setText(e.target.value);
  };

  for (let i = 0; i < list.length; i++) {
    console.log("This is the length of the list after loop: " + list.length);
    console.log("This is the list: " + list);
    items.push(<ListItems title={list[i]} key={i} />);
  }

  return (
    <>
      <div>
        <form>
          <input
            type="text"
            placeholder="Enter task"
            onChange={handleChange}
            value={inputText}
          />
          <input
            type="button"
            value="Enter"
            onClick={() => {
              addItem(inputText);
            }}
          />
        </form>
        {items}
      </div>
    </>
  );
}

export { InputTask };
