package model;

import java.util.List;

public interface IRule {
    public boolean predicate(List<DataRow> data, int index);
}
