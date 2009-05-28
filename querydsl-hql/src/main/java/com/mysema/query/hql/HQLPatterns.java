/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.hql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mysema.query.serialization.OperationPatterns;
import com.mysema.query.types.operation.Operator;
import com.mysema.query.types.operation.OperatorImpl;
import com.mysema.query.types.operation.Ops;
import com.mysema.query.types.path.PathType;

/**
 * HQLPatterns extends OperationPatterns to provide operator patterns for HQL
 * serialization
 * 
 * @author tiwe
 * @version $Id$
 */
public class HQLPatterns extends OperationPatterns {

    public static final HQLPatterns DEFAULT = new HQLPatterns();

    public static final List<Operator<?>> wrapCollectionsForOp;

    static {
        wrapCollectionsForOp = Collections.<Operator<?>> unmodifiableList(Arrays.<Operator<?>> asList(
            Ops.IN, 
            Ops.NOTIN, 
            Ops.QuantOps.ALL, 
            Ops.QuantOps.ANY,
            Ops.QuantOps.AVG_IN_COL, 
            Ops.QuantOps.EXISTS, 
            Ops.QuantOps.NOTEXISTS));
    }

    public HQLPatterns() {
        // boolean
        add(Ops.AND, "%s and %s", 36);
        add(Ops.NOT, "not %s", 3);
        add(Ops.OR, "%s or %s", 38);
        add(Ops.XNOR, "%s xnor %s", 39);
        add(Ops.XOR, "%s xor %s", 39);

        // comparison
        add(Ops.BETWEEN, "%s between %s and %s", 30);
        add(Ops.NOTBETWEEN, "%s not between %s and %s", 30);

        // numeric
        add(Ops.MathOps.SQRT, "sqrt(%s)");

        // various        
        add(Ops.ISNULL, "%s is null", 26);
        add(Ops.ISNOTNULL, "%s is not null", 26);
        
        // collection
        add(Ops.IN, "%s in %s");
        add(Ops.NOTIN, "%s not in %s");
        add(Ops.COL_ISEMPTY, "%s is empty");
        add(Ops.COL_ISNOTEMPTY, "%s is not empty");
        add(Ops.COL_SIZE, "%s.size");
        
        // string
        add(Ops.CONCAT, "%s || %s", 37);
        add(Ops.LIKE, "%s like %s", 27);
        add(Ops.LOWER, "lower(%s)");
        add(Ops.SUBSTR1ARG, "substring(%s,%s)");
        add(Ops.SUBSTR2ARGS, "substring(%s,%s,%s)");
        add(Ops.TRIM, "trim(%s)");
        add(Ops.UPPER, "upper(%s)");

        // date time
        add(Ops.DateTimeOps.SYSDATE, "sysdate");
        add(Ops.DateTimeOps.CURRENT_DATE, "current_date()");
        add(Ops.DateTimeOps.CURRENT_TIME, "current_time()");
        add(Ops.DateTimeOps.CURRENT_TIMESTAMP, "current_timestamp()");
        add(Ops.DateTimeOps.SECOND, "second(%s)");
        add(Ops.DateTimeOps.MINUTE, "minute(%s)");
        add(Ops.DateTimeOps.HOUR, "hour(%s)");
        add(Ops.DateTimeOps.DAY, "day(%s)");
        add(Ops.DateTimeOps.MONTH, "month(%s)");
        add(Ops.DateTimeOps.YEAR, "year(%s)");

        // path types
        for (PathType type : new PathType[] { PathType.LISTVALUE,
                PathType.LISTVALUE_CONSTANT, PathType.MAPVALUE,
                PathType.MAPVALUE_CONSTANT }) {
            add(type, "%s[%s]");
        }
        add(PathType.PROPERTY, "%s.%s");
        
        add(PathType.VARIABLE, "%s");

        // HQL types
//        add(HqlPathType.MINELEMENT, "minelement(%s)");
//        add(HqlPathType.MAXELEMENT, "maxelement(%s");
//        add(HqlPathType.MININDEX, "minindex(%s)");
//        add(HqlPathType.MAXINDEX, "maxindex(%s)");
//        add(HqlPathType.LISTINDICES, "indices(%s)");
//        add(HqlPathType.MAPINDICES, "indices(%s)");
    }
    
}
