def binary_search(arr, num, left = nil, right = nil)
  return binary_search(arr, num, 0, arr.length) if !left && !right

  pos = left + ((right - left) / 2)

  return arr.last == num if pos >= arr.length - 1
  return arr.first == num if pos <= 0

  if arr[pos] > num
    binary_search arr, num, left, pos
  elsif arr[pos] < num
    binary_search arr, num, pos, right
  else
    true
  end
end

def linear_search(arr, num)
  arr.each { |x| return true if num == x }
  false
end

def test(m, arr, times, options = {})
  tests = (0...times)

  start = Time.now
  tests.each do |t|
    unless send(m, arr, t * (arr.length / tests.last))
      fail "bad result for #{options[:name]}" 
    end
  end

  puts "#{tests.count} #{options[:name]} searches took #{(Time.now - start) * 1000}ms"

  if send(m, arr, -1) || send(m, arr, arr.last + 1)
    fail "bad result for #{options[:name]}"
  end
end

arr = (0...1_000_000).to_a
tests = 200

test :linear_search, arr, tests, name: 'linear'

test :binary_search, arr, tests, name: 'binary'
