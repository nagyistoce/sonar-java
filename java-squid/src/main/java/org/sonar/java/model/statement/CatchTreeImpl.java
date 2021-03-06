/*
 * SonarQube Java
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.java.model.statement;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import org.sonar.java.model.InternalSyntaxToken;
import org.sonar.java.model.JavaTree;
import org.sonar.java.model.declaration.VariableTreeImpl;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.SyntaxToken;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TreeVisitor;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.Iterator;

public class CatchTreeImpl extends JavaTree implements CatchTree {

  private final InternalSyntaxToken catchToken;
  private final InternalSyntaxToken openParenToken;
  private final VariableTree parameter;
  private final BlockTree block;
  private final InternalSyntaxToken closeParenToken;

  public CatchTreeImpl(InternalSyntaxToken catchToken, InternalSyntaxToken openParenToken, VariableTreeImpl parameter, InternalSyntaxToken closeParenToken, BlockTreeImpl block) {
    super(Kind.CATCH);

    this.catchToken = catchToken;
    this.openParenToken = openParenToken;
    this.parameter = Preconditions.checkNotNull(parameter);
    this.closeParenToken = closeParenToken;
    this.block = Preconditions.checkNotNull(block);

    addChild(catchToken);
    addChild(openParenToken);
    addChild(parameter);
    addChild(closeParenToken);
    addChild(block);
  }

  @Override
  public Kind getKind() {
    return Kind.CATCH;
  }

  @Override
  public SyntaxToken catchKeyword() {
    return catchToken;
  }

  @Override
  public SyntaxToken openParenToken() {
    return openParenToken;
  }

  @Override
  public VariableTree parameter() {
    return parameter;
  }

  @Override
  public SyntaxToken closeParenToken() {
    return closeParenToken;
  }

  @Override
  public BlockTree block() {
    return block;
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitCatch(this);
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.<Tree>forArray(
      parameter,
      block);
  }

}
