/***************************************************************
 * Copyright (c) 2014 - 2015 , gurkenlabs, All rights reserved *
 ***************************************************************/
package de.gurkenlabs.litiengine.environment.tilemap;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * This interface represents a non graphical instance on a map that can define
 * vaious things for an engine. e.g. it can be used to define static collision
 * boxes or other special regions on the map.
 */
public interface IMapObject extends ICustomPropertyProvider {

  /**
   * Gets the dimension.
   *
   * @return the dimension
   */
  public Dimension getDimension();

  /**
   * Gets the grid id.
   *
   * @return the grid id
   */
  public int getGridId();

  /**
   * Gets the hit box.
   *
   * @return the hit box
   */
  public Rectangle2D getBoundingBox();

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId();

  /**
   * Gets the location.
   *
   * @return the location
   */
  public Point getLocation();

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName();

  public String getType();

  public IPolyline getPolyline();

  public void setGid(int gid);

  public void setHeight(double height);

  public void setId(int id);

  public void setName(String name);

  public void setType(String type);

  public void setWidth(double width);

  public void setX(double x);

  public void setY(double y);

  public double getX();

  public double getY();

  public double getWidth();

  public double getHeight();

}
