// 1. create bin/ dir
// 2. compile with with:
//      $ rustc binary_search.rs -o bin/binary_search
// 3. run with:
//      $ ./bin/binary_search

extern crate time;

fn binary_search(arr: &[int], num: int, left: uint, right: uint) -> bool {
    let pos = (left as int + (right - left) as int / 2) as uint;

    if pos >= arr.len() - 1 { return arr[arr.len() - 1] == num }
    if pos <= 0 {   
        return arr[0] == num
    }

    if arr[pos] > num {
        binary_search(arr, num, left, pos) 
    } else if arr[pos] < num {
        binary_search(arr, num, pos, right)
    } else {
        true
    }
}

fn linear_search(arr: &[int], num: int) -> bool {
    for x in range(0u, arr.len()) {
        if arr[x] == num { return true }
    }
    false
}

fn main() {
    let size = 1_000_000u;

    let vec: Vec<int> = Vec::from_fn(size, |i| i as int);
    let arr = vec.as_slice();

    let tests = 500;

    let mut time = time::precise_time_s();
    for x in range(0u, tests) {
        assert!(linear_search(arr, (x * (size / tests)) as int));
    }

    println!("{} linear searches took {}ms", tests, (time::precise_time_s() - time) * 1000.0);

    time = time::precise_time_s();
    for x in range(0u, tests) {
        assert!(binary_search(arr, (x * (size / tests)) as int, 0, arr.len()));
    }
    println!("{} binary searches took {}ms", tests, (time::precise_time_s() - time) * 1000.0);
}
