import networkx as nx
import matplotlib.pyplot as plt
from collections import deque

import numpy as np


def is_valid_move(state, src, dest):
    if not state[src]:  # No disk to move
        return False
    if not state[dest] or state[src][-1] < state[dest][-1]:  # Valid move
        return True
    return False


def apply_move(state, src, dest):
    new_state = [list(rod) for rod in state]  # Create a copy of the state
    disk = new_state[src].pop()
    new_state[dest].append(disk)
    return tuple(tuple(rod) for rod in new_state)


def generate_all_moves(n):
    initial_state = (tuple(range(n, 0, -1)), (), ())
    G = nx.DiGraph()
    G.add_node(initial_state)

    queue = deque([initial_state])
    all_moves = {}

    while queue:
        current_state = queue.popleft()

        for src in range(3):
            for dest in range(3):
                if src != dest and is_valid_move(current_state, src, dest):
                    new_state = apply_move(current_state, src, dest)
                    if new_state not in G:
                        G.add_node(new_state)
                        queue.append(new_state)
                    G.add_edge(current_state, new_state)
                    all_moves[(current_state, new_state)] = (src, dest)

    return G, all_moves


def hanoi_moves(n, source, target, auxiliary):
    moves = []

    def solve(n, source, target, auxiliary):
        if n == 1:
            moves.append((source, target))
        else:
            solve(n - 1, source, auxiliary, target)
            moves.append((source, target))
            solve(n - 1, auxiliary, target, source)

    solve(n, source, target, auxiliary)
    return moves


def tree_layout_positions(G, initial_state, optimal_path):
    levels = {initial_state: 0}
    positions = {}
    queue = deque([initial_state])
    max_width = {0: 1}  # Tracks the maximum width of each level
    optimal_set = set(optimal_path)  # Convert list to set for fast lookup

    while queue:
        state = queue.popleft()
        level = levels[state]
        next_level = level + 1

        children = [neighbor for neighbor in G.successors(state) if neighbor not in levels]
        for child in children:
            levels[child] = next_level
            max_width[next_level] = max_width.get(next_level, 0) + 1
            queue.append(child)

    # Assign positions
    level_offset = {}
    for node, level in levels.items():
        if level not in level_offset:
            level_offset[level] = 0
        if node in optimal_set:
            # Place this node on the rightmost edge of its level
            x_position = max_width[level] - 1  # Rightmost position
        else:
            # Place other nodes on the left, moving the offset as we add nodes
            x_position = level_offset[level]
            level_offset[level] += 1  # Increment to place next node further left

        positions[node] = (x_position, -level)

    # Adjust positions so that the optimal path aligns to the right
    for level in range(max(levels.values()) + 1):
        if max_width[level] > 1:
            shift = (max_width[level] - 1) / 2
            for node in levels:
                if levels[node] == level:
                    # Shift all nodes at this level to center around the optimal path
                    positions[node] = (positions[node][0] - shift, positions[node][1])

    return positions

def visualize_tree(G, n, optimal_moves):
    initial_state = (tuple(range(n, 0, -1)), (), ())
    target_state = ((), (), tuple(range(n, 0, -1)))

    # Convert move list to path of states
    path_states = [initial_state]
    current_state = initial_state
    for src, dest in optimal_moves:
        next_state = apply_move(current_state, src, dest)
        path_states.append(next_state)
        current_state = next_state

    # Convert states to edges
    path_edges = [(path_states[i], path_states[i + 1]) for i in range(len(path_states) - 1)]

    pos = tree_layout_positions(G, initial_state, path_states)  # Get custom layout positions
    plt.figure(figsize=(12, 8))
    nx.draw(G, pos, with_labels=True, node_size=500, node_color='lightblue', font_color='gray', font_size=10, font_weight='bold', arrowsize=20)
    nx.draw_networkx_edges(G, pos, edgelist=path_edges, edge_color='r', width=2)

    plt.show()

def read_n_disks_from_file(file_path):
    try:
        with open(file_path, 'r') as file:
            return int(file.read().strip())
    except FileNotFoundError:
        print("File not found. Using default value of 3.")
        return 3
    except ValueError:
        print("Invalid number in file. Using default value of 3.")
        return 3

def main():
    n = read_n_disks_from_file("nDisks.txt")
    G, all_moves = generate_all_moves(n)
    optimal_moves = hanoi_moves(n, 0, 2, 1)
    visualize_tree(G, n, optimal_moves)

if __name__ == "__main__":
    main()
