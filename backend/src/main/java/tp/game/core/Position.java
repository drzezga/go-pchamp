package tp.game.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonFormat(shape=JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "x", "y" })
public record Position(int x, int y) { }