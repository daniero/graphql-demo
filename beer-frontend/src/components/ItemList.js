import React from 'react';
import classnames from "classnames";
import style from "./ItemList.module.css";

export const ItemList = ({ items = [], selected, setSelected, ...props }) => {
  return (
    <div {...props}>
      {items.map(item => {
        const itemSelected = selected && selected.id === item.id;

        return (
          <div
            key={item.id}
            className={classnames('clickable', style.item, {
              [style.selected]: itemSelected
            })}
            onClick={() => setSelected(item)}
          >
            {item.name}
          </div>
        );
      })}
    </div>
  );
};