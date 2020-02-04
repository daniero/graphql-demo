import React from 'react';
import { gql } from "apollo-boost";
import { useQuery } from "@apollo/react-hooks";
import classnames from 'classnames';
import style from "./Brewery.module.css";


const query = gql`
    query getBrewery($beerId: ID!) {
        beer(id: $beerId) {
            id
            name
            style
            abv
            ounces
            ibu
        }
    }
`;


function percentage(x) {
  return Math.round(x * 1000) / 10;
}

export const Beer = ({ beer }) => {

  const { loading, error, data } = useQuery(query, {
    variables: {
      beerId: beer.id
    }
  });

  const fetched = data && data.beer;

  return (
    <div
      className={classnames({
        [style.loading]: loading
      })}
    >
      <h1>{beer.name}</h1>
      {fetched &&
      <>
        <h2>{fetched.style}</h2>
        <h3>
          {fetched.abv && <span>{percentage(fetched.abv)}% </span>}
          {fetched.ounces && <span>{fetched.ounces}oz </span>}
          {fetched.ibu && <span>{fetched.ibu}ibu</span>}
        </h3>
      </>
      }
    </div>
  );
};