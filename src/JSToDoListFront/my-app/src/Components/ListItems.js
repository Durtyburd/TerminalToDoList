import { DeleteList } from "./DeleteList";

function ListItems(props) {
  return (
    <li>
      <button>x</button>
      {props.title}
    </li>
  );
}

export { ListItems };
