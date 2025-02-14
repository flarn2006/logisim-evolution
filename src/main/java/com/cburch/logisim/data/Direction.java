/*
 * Logisim-evolution - digital logic design tool and simulator
 * Copyright by the Logisim-evolution developers
 *
 * https://github.com/logisim-evolution/
 *
 * This is free software released under GNU GPLv3 license
 */

package com.cburch.logisim.data;

import static com.cburch.logisim.data.Strings.S;

import com.cburch.logisim.util.StringGetter;

public class Direction implements AttributeOptionInterface {
  public static Direction parse(String str) {
    if (str.equals(EAST.name)) return EAST;
    if (str.equals(WEST.name)) return WEST;
    if (str.equals(NORTH.name)) return NORTH;
    if (str.equals(SOUTH.name)) return SOUTH;
    throw new NumberFormatException("illegal direction '" + str + "'");
  }

  public static final Direction EAST =
      new Direction("east", S.getter("directionEastOption"), S.getter("directionEastVertical"), 0);
  public static final Direction NORTH =
      new Direction("north", S.getter("directionNorthOption"), S.getter("directionNorthVertical"), 1);
  public static final Direction WEST =
      new Direction("west", S.getter("directionWestOption"), S.getter("directionWestVertical"), 2);
  public static final Direction SOUTH =
      new Direction("south", S.getter("directionSouthOption"), S.getter("directionSouthVertical"), 3);

  public static final Direction[] cardinals = {EAST, NORTH, WEST, SOUTH};

  private final String name;
  private final StringGetter disp;
  private final StringGetter vert;
  private final int id;

  private Direction(String name, StringGetter disp, StringGetter vert, int id) {
    this.name = name;
    this.disp = disp;
    this.vert = vert;
    this.id = id;
  }

  public boolean equals(Direction other) {
    return this.id == other.id;
  }

  public StringGetter getDisplayGetter() {
    return disp;
  }

  public Direction getLeft() {
    return cardinals[(id + 1) % 4];
  }

  public Direction getRight() {
    return cardinals[(id + 3) % 4];
  }

  // for AttributeOptionInterface
  public Object getValue() {
    return this;
  }

  @Override
  public int hashCode() {
    return id;
  }

  public Direction reverse() {
    return cardinals[(id + 2) % 4];
  }

  public int toDegrees() {
    return id * 90;
  }

  public String toDisplayString() {
    return disp.toString();
  }

  public double toRadians() {
    return id * Math.PI / 2.0;
  }

  @Override
  public String toString() {
    return name;
  }

  public String toVerticalDisplayString() {
    return vert.toString();
  }
}
