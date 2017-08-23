extern crate rand;

use std::io;
use rand::Rng;

fn main() {
  loop {
    println!("enter the number of coin flips:");

    let mut n = String::new();

    io::stdin().read_line(&mut n)
      .expect("Failed to read line");

    let n: u32 = match n.trim().parse() {
      Ok(num) => num,
      Err(_) => continue,
    };

    let mut count_sets_flips = 0;
    loop {
      let result = flip_coins(n);
      count_sets_flips = count_sets_flips + 1;
      if result[0] == 0 || result[1] == 0 {
        break;
      }
    }
    println!("It took {} sets of {} flips for them all to land on one side", count_sets_flips, n);
  }
}

fn flip_coins(n: u32) -> [i32; 2] {
  let mut count_heads = 0;
  let mut count_tails = 0;

  for _ in 0..n {
    let result = rand::thread_rng().gen_range(0, 2);
    if result == 0 {
      count_heads = count_heads + 1;
    } else {
      count_tails = count_tails + 1;
    } 
  }

  return [count_heads, count_tails];
}