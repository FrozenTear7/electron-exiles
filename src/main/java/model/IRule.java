package model;

import java.util.List;

public interface IRule {
    boolean predicate(List<DataRow> data, int index);
}
