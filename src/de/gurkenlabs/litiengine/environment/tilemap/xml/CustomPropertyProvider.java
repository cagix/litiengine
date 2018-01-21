package de.gurkenlabs.litiengine.environment.tilemap.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import de.gurkenlabs.litiengine.environment.tilemap.ICustomPropertyProvider;

public class CustomPropertyProvider implements ICustomPropertyProvider, Serializable {
  private static final long serialVersionUID = 7418225969292279565L;

  /** The properties. */
  @XmlElementWrapper(name = "properties")
  @XmlElement(name = "property")
  private List<Property> properties = new ArrayList<>();

  @Override
  public String getCustomProperty(final String name) {
    if (this.properties != null && this.properties.stream().anyMatch(x -> x.getName().equals(name))) {
      Optional<Property> opt = this.properties.stream().filter(x -> x.getName().equals(name)).findFirst();
      if (opt.isPresent()) {
        return opt.get().getValue();
      }
    }

    return null;
  }

  @Override
  public void setCustomProperty(String name, String value) {
    if (this.properties == null || name == null) {
      return;
    }

    Optional<Property> opt = this.properties.stream().filter(x -> x.getName().equals(name)).findFirst();
    if (opt.isPresent()) {
      opt.get().setValue(value);
      return;
    }

    this.properties.add(new Property(name, value));
  }

  @Override
  @XmlTransient
  public List<Property> getAllCustomProperties() {
    if (this.properties == null) {
      return new ArrayList<>();
    }

    return this.properties;
  }

  @Override
  public void setCustomProperties(List<Property> props) {
    if (props == null) {
      this.properties = null;
      return;
    }
    if (this.properties != null) {
      this.properties.clear();
    } else {
      this.properties = new ArrayList<>();
    }

    for (Property prop : props) {
      this.setCustomProperty(prop.getName(), prop.getValue());
    }
  }

  @Override
  public int getCustomPropertyInt(String name) {
    return this.getCustomPropertyInt(name, 0);
  }

  @Override
  public int getCustomPropertyInt(String name, int defaultValue) {
    String value = this.getCustomProperty(name);
    if (value == null || value.isEmpty()) {
      return defaultValue;
    }

    return Integer.parseInt(value);
  }

  @Override
  public boolean getCustomPropertyBool(String name) {
    return this.getCustomPropertyBool(name, false);
  }

  @Override
  public boolean getCustomPropertyBool(String name, boolean defaultValue) {
    String value = this.getCustomProperty(name);
    if (value == null || value.isEmpty()) {
      return defaultValue;
    }

    return Boolean.valueOf(value);
  }

  @Override
  public float getCustomPropertyFloat(String name) {
    return this.getCustomPropertyFloat(name, 0);
  }

  @Override
  public float getCustomPropertyFloat(String name, float defaultValue) {
    String value = this.getCustomProperty(name);
    if (value == null || value.isEmpty()) {
      return defaultValue;
    }

    return Float.valueOf(value);
  }

  @Override
  public double getCustomPropertyDouble(String name) {
    return this.getCustomPropertyDouble(name, 0);
  }

  @Override
  public double getCustomPropertyDouble(String name, double defaultValue) {
    String value = this.getCustomProperty(name);
    if (value == null || value.isEmpty()) {
      return defaultValue;
    }

    return Double.valueOf(value);
  }

  void beforeMarshal(Marshaller m) {
    if (this.properties != null && this.properties.isEmpty()) {
      this.properties = null;
    }
  }
}
