# RubixCubeSolver
Solver for rubix cubes.  Currently working on 2x2 cube (will work on 3x3 once that's finished).
Deliverately avoided looking at other solutions to the problem until I've got one in place.

Right now got the various steps working (rotate left, right, forward, backward, clockwise, counter clockwise).
I'm currently using a convention of height, width, depth 0..size of cube -1.
0,0,0 doesn't move (intentionally) so that I always have a reference point from which to calculate a hashcode of the current state.

Now to implement a way of generating all possible solutions, and then finding the current state, and reversing the steps from how it got there.

Once that's done, I'd like to find a good way to capture cube state without manual entry of each piece.  
