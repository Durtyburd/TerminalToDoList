import { ListItems } from "./ListItems";
import React from "react";

function InputTask({ listItem, addItem, inputText, setText }) {
  const handleChange = (e) => {
    setText(e.target.value);
  };

  const items = listItem.map((listItem, i) => (
    <ListItems title={listItem} key={i} />
  ));

  return (
    <div>
      <form>
        <input
          type="text"
          placeholder="Enter task"
          onChange={handleChange}
          value={inputText}
        />
        <input type="button" value="Enter" onClick={() => addItem(inputText)} />
      </form>
      <div>
        <ul> {items}</ul>
      </div>
    </div>
  );
}

export { InputTask };
