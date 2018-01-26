/*
 *
 *    Copyright 2016 jshook
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package activityconfig.rawyaml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class StmtVariationTests {

    private final static Logger logger = LoggerFactory.getLogger(StmtVariationTests.class);

    @Test
    public void testListStmtsOnly() {
        RawYamlStatementLoader ysl = new RawYamlStatementLoader();
        RawStmtsDocList docs = ysl.loadString(logger,
                "statements:\n" +
                        " - first statement\n" +
                        " - second statement\n"
        );

        assertThat(docs.getStmtsDocs()).hasSize(1);
        RawStmtsDoc doc = docs.getStmtsDocs().get(0);
        assertThat(doc.getRawStmtDefs()).hasSize(2);
        List<RawStmtDef> stmts = doc.getRawStmtDefs();
        RawStmtDef s0 = stmts.get(0);
        assertThat(s0.getName()).isEqualTo("stmt1");
        assertThat(s0.getStmt()).isEqualTo("first statement");
        RawStmtDef s1 = stmts.get(1);
        assertThat(s1.getName()).isEqualTo("stmt2");
        assertThat(s1.getStmt()).isEqualTo("second statement");
    }

    @Test
    public void testSingleEntryMapStmtsOnly() {
        RawYamlStatementLoader ysl = new RawYamlStatementLoader();
        RawStmtsDocList docs = ysl.loadString(logger,
                "statements:\n" +
                        " - s1: statement one\n" +
                        " - s2: statement two\n"
        );
        assertThat(docs.getStmtsDocs()).hasSize(1);
        RawStmtsDoc doc = docs.getStmtsDocs().get(0);
        assertThat(doc.getRawStmtDefs()).hasSize(2);
        List<RawStmtDef> stmts = doc.getRawStmtDefs();
        assertThat(stmts.get(0)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(0).getName()).isEqualTo("s1");
        assertThat(stmts.get(0).getStmt()).isEqualTo("statement one");
        assertThat(stmts.get(1)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(1).getName()).isEqualTo("s2");
        assertThat(stmts.get(1).getStmt()).isEqualTo("statement two");
    }

    @Test
    public void testMapStmtsOnly() {
        RawYamlStatementLoader ysl = new RawYamlStatementLoader();
        RawStmtsDocList docs = ysl.loadString(logger,
                "statements:\n" +
                        " - name: s1\n" +
                        "   stmt: statement one\n" +
                        " - name: s2\n" +
                        "   stmt: statement two\n"
        );
        assertThat(docs.getStmtsDocs()).hasSize(1);
        RawStmtsDoc doc = docs.getStmtsDocs().get(0);
        assertThat(doc.getRawStmtDefs()).hasSize(2);
        List<RawStmtDef> stmts = doc.getRawStmtDefs();
        assertThat(stmts.get(0)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(0).getName()).isEqualTo("s1");
        assertThat(stmts.get(0).getStmt()).isEqualTo("statement one");
        assertThat(stmts.get(1)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(1).getName()).isEqualTo("s2");
        assertThat(stmts.get(1).getStmt()).isEqualTo("statement two");
    }

    public void testMixedForms() {
        RawYamlStatementLoader ysl = new RawYamlStatementLoader();
        RawStmtsDocList docs = ysl.loadString(logger,
                "statement:\n" +
                        " - name: s1\n" +
                        "   stmt: statement one\n" +
                        " - statement two\n" +
                        " - s3: statement three\n" +
                        " - ST4: statement four\n" +
                        "   type: organic\n"
        );
        assertThat(docs.getStmtsDocs()).hasSize(1);
        RawStmtsDoc doc = docs.getStmtsDocs().get(0);
        assertThat(doc.getRawStmtDefs()).hasSize(4);
        List<RawStmtDef> stmts = doc.getRawStmtDefs();
        assertThat(stmts.get(0)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(0).getName()).isEqualTo("s1");
        assertThat(stmts.get(0).getStmt()).isEqualTo("statement one");
        assertThat(stmts.get(1)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(1).getName()).isEqualTo("stmt2");
        assertThat(stmts.get(1).getStmt()).isEqualTo("statement two");
        assertThat(stmts.get(2)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(2).getName()).isEqualTo("s3");
        assertThat(stmts.get(2).getStmt()).isEqualTo("statement three");
        assertThat(stmts.get(3)).isOfAnyClassIn(RawStmtDef.class);
        assertThat(stmts.get(3).getName()).isEqualTo("ST4");
        assertThat(stmts.get(3).getStmt()).isEqualTo("statement four");
        assertThat(stmts.get(3).getParams().get("type")).isEqualTo("organic");

    }
}
