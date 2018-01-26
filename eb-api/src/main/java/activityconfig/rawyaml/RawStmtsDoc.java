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

import java.util.ArrayList;
import java.util.List;

/**
 * A statements doc can have both a list of statement blocks and/or a
 * list of statements. It can also have all the block parameters
 * assignable to {@link BlockParams}.
 * <p>
 * The reason for having support both statements or statement blocks
 * is merely convenience. If you do not need or want to deal with the
 * full blocks format, the extra structure gets in the way.
 */
public class RawStmtsDoc extends StatementsOwner {

    private List<RawStmtsBlock> blocks = new ArrayList<>();

    /**
     * Return the list of statement blocks in this RawStmtsDoc.
     * If raw statements are defined on this RawStmtsDoc, then a single
     * StmtBlock containing those statements is prepended to the block list.
     * Otherwise, the list of StmtBlocks is returned as-is.
     *
     * @return all logical statement blocks containing statements
     */
    public List<RawStmtsBlock> getBlocks() {
        List<RawStmtsBlock> stmtBlocks = new ArrayList<>();
        if (!getRawStmtDefs().isEmpty()) {
            RawStmtsBlock rawStmtsBlock = new RawStmtsBlock();
            rawStmtsBlock.setName("block0");
            rawStmtsBlock.setRawStmtDefs(getRawStmtDefs());
            stmtBlocks.add(rawStmtsBlock);
        }
        stmtBlocks.addAll(this.blocks);
        return stmtBlocks;
    }

    public void setBlocks(List<RawStmtsBlock> blocks) {
        this.blocks.clear();
        this.blocks.addAll(blocks);
    }
}
