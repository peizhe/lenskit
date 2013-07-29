/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2013 Regents of the University of Minnesota and contributors
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.collections;

import javax.annotation.Nonnull;

import it.unimi.dsi.fastutil.longs.LongList;

import org.grouplens.lenskit.vectors.SparseVector;

/**
 * {@link LongList} that has double-valued scores for each entry. Undefined
 * scores are stored as {@link Double#NaN}.
 *
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 * @compat Public
 */
public interface ScoredLongList extends LongList {
    /**
     * Add a scored item at a particular position.
     *
     * @param index The position for the new item.
     * @param item  The item value.
     * @param score The item score.
     * @see #add(int, long)
     */
    void add(int index, long item, double score);

    /**
     * Add a scored item to the list.
     *
     * @param item  The item to add.
     * @param score The item's score.
     * @return {@code true} if the collection was modified.
     * @see #add(long)
     */
    boolean add(long item, double score);

    /**
     * Add scored elements to the list.
     *
     * @param index  The index at which to insert elements.
     * @param items  The items.
     * @param scores The scores.
     * @see #addElements(int, long[])
     */
    void addElements(int index, @Nonnull long[] items, @Nonnull double[] scores);

    /**
     * Add scored elements to the list.
     *
     * @param index  The index at which to insert elements.
     * @param items  The items.
     * @param scores The scores.
     * @param offset The offset in {@var items} of the first element to add.
     * @param length The number of elements to add.
     * @see #addElements(int, long[], int, int)
     */
    void addElements(int index, @Nonnull long[] items, @Nonnull double[] scores,
                     int offset, int length);

    /**
     * Copies elements into the given arrays.
     *
     * @param from   The index in the list at which to start copying.
     * @param items  The array to receive the items.
     * @param scores The array to receive the scores.
     * @param offset The offset in {@var items} and {@var scores} at
     *               which to start copying.
     * @param length The number of elements to copy.
     * @see #getElements(int, long[], int, int)
     */
    void getElements(int from, long[] items, double[] scores,
                     int offset, int length);

    /**
     * Get the score for the item at a position.
     *
     * @param index The index.
     * @return The score for the item at position {@var i}.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    double getScore(int index);

    /**
     * Set the score for the item at a position.
     *
     * @param index The index.
     * @param score The new score.
     * @return The old score.
     */
    double setScore(int index, double score);

    @Override
    ScoredLongListIterator iterator();

    @Override
    ScoredLongListIterator listIterator();

    /**
     * Return a sparse vector mapping items to scores.
     *
     * @return A vector whose keys are the items and values the scores in this
     *         list. {@link Double#NaN} values are preserved and their items are
     *         included in the key set.
     */
    SparseVector scoreVector();
}