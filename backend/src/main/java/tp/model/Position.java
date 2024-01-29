package tp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonFormat(shape=JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "x", "y" })
public record Position(int x, int y) implements Serializable { }