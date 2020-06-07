package de.gurkenlabs.litiengine.graphics.emitters.particles;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class PolygonParticle extends ShapeParticle {
  private int sides;

  public PolygonParticle(float width, float height, Color color, int sides) {
    super(width, height, color);
    this.sides = sides;
  }

  @Override
  protected Shape getShape(Point2D emitterOrigin) {
    Polygon p = new Polygon();
    float x = this.getAbsoluteX(emitterOrigin);
    float y = this.getAbsoluteY(emitterOrigin);
    float theta = (float) (2 * Math.PI / this.sides);
    for (int i = 0; i < this.sides; i++) {
      p.addPoint((int) (x + this.getWidth() * Math.cos(theta * i)), (int) (y + this.getHeight() * Math.sin(theta * i)));
    }
    final AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(this.getAngle()), this.getAbsoluteX(emitterOrigin) + this.getWidth() * 0.5, this.getAbsoluteY(emitterOrigin) + this.getHeight() * 0.5);
    return rotate.createTransformedShape(p);
  }

}
